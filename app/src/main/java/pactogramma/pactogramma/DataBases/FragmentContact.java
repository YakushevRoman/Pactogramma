package pactogramma.pactogramma.DataBases;

import android.Manifest;

import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentResolver;


import android.content.Context;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Data;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import pactogramma.pactogramma.R;

public class FragmentContact extends Fragment {
    public static final String OUTPUT_FILE_WHATSAPP = "outputfile.txt";
    public static final String OUTPUT_WHATSAPP = "whatsapp.txt";
    public static final String TAG = "WhatsApp";
    private static final int REQUEST_CODE_READ_CONTACTS = 1;
    private static final int REQUEST_PERMISSION_WRITE = 1001;
    private static boolean READ_CONTACTS_GRANTED = false;
    private Object object;
    ListView contactList;
    private List <String> listContacts;
    private boolean permissionGranted = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);
        contactList = view.findViewById(R.id.contactList);

        listContacts = new ArrayList<>();
        /*формирования литси из файла*/
        listContacts.add("+380631348299");
        listContacts.add("+380672566867");
        listContacts.add("+380502741401");
        listContacts.add("+380952011179");
        listContacts.add("+380954666068");
        listContacts.add("+380676072221");
        // получаем разрешения
        int hasReadContactPermission = ContextCompat.checkSelfPermission(Objects.requireNonNull(getContext()), Manifest.permission.READ_CONTACTS);
        // если устройство до API 23, устанавливаем разрешение
        if (hasReadContactPermission == PackageManager.PERMISSION_GRANTED) {
            READ_CONTACTS_GRANTED = true;
        } else {
            // вызываем диалоговое окно для установки разрешений
            ActivityCompat.requestPermissions(Objects.requireNonNull(getActivity()), new String[]{Manifest.permission.READ_CONTACTS}, REQUEST_CODE_READ_CONTACTS);
        }
        // получаем разрешения
        int hasReadContactPermission1 = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_CONTACTS);
        // если устройство до API 23, устанавливаем разрешение
        if (hasReadContactPermission1 == PackageManager.PERMISSION_GRANTED) {
            READ_CONTACTS_GRANTED = true;
        } else {
            // вызываем диалоговое окно для установки разрешений
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_CONTACTS}, REQUEST_CODE_READ_CONTACTS);
        }
        // если разрешение установлено, загружаем контакты
        if (READ_CONTACTS_GRANTED) {

            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (String number: listContacts) {
                        writeContact(number);
                    }
                    Log.d(TAG, "run: 1 thread" + " Данные добавлены" );
                }
            });

            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {TimeUnit.SECONDS.sleep(5);
                        loadContacts();
                        Log.d(TAG, "run: 2 thread" + " Данные считаны" );
                    } catch (OperationApplicationException | RemoteException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

            Thread t3 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        whatsappload();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.d(TAG, "run: 3 thread" + " воцап" );
                }
            });

            t1.start();
            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            t3.start();
            try {
                t3.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            t2.start();
        }
        return view;
    }

    private synchronized void whatsappload() throws InterruptedException {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
        sendIntent.setType("text/plain");
        sendIntent.setPackage("com.whatsapp");
        startActivity(sendIntent);
    }

    private synchronized void   writeContact(String phoneNumber) {
        // для добавления контакта в базу телефона
        if (!phoneNumber.equals("")) {
            ArrayList<ContentProviderOperation> ops = new ArrayList<>();
            int rawContactInsertIndex = ops.size();

            ops.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                    .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                    .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
                    .build());
            ops.add(ContentProviderOperation
                    .newInsert(Data.CONTENT_URI)
                    .withValueBackReference(Data.RAW_CONTACT_ID, rawContactInsertIndex)
                    .withValue(Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                    .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, phoneNumber) // Name of the person
                    .build());
            ops.add(ContentProviderOperation
                    .newInsert(Data.CONTENT_URI)
                    .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactInsertIndex)
                    .withValue(Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                    .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, phoneNumber) // Number of the person
                    .withValue(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)
                    .build()); // Type of mobile number
            try {
                ContentProviderResult[] res = Objects.requireNonNull(getActivity()).getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
            } catch (RemoteException | OperationApplicationException e) {
                // error
            }
        }
    }

    private synchronized void loadContacts() throws OperationApplicationException, RemoteException {
        FileOutputStream fileOutputStream = null;
        FileOutputStream fos = null;
        final String[] projection = {
                Data.DATA3
        };

        final String selection = Data.MIMETYPE + " =? and account_type=?";
        final String[] selectionArgs = {
                "vnd.android.cursor.item/vnd.com.whatsapp.profile",
                "com.whatsapp"
        };
        ContentResolver cr = getActivity().getContentResolver();
        Cursor c = cr.query(
                Data.CONTENT_URI,
                projection,
                selection,
                selectionArgs,
                null);

        try {
            //
            fileOutputStream = getActivity().openFileOutput(OUTPUT_FILE_WHATSAPP, Context.MODE_PRIVATE);
            //
            if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                Log.d(TAG, "loadContacts: " + "Хранилище недоступно");
            }
            fos = new FileOutputStream(getExternalPaht());
            while (c.moveToNext()) {
                String dataWhatsapp = c.getString(0);
                String number = dataWhatsapp.substring(9) + "\n";
                /*реализуем запись в файл*/
                fileOutputStream.write(number.getBytes());
                // для записи на внешнее хранилище
                savePhone(number, fos);
                Log.d("WhatsApp",  number);
            }
            Log.d("WhatsApp",  "Данные сохранены" + fileOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (fileOutputStream != null){
                    fileOutputStream.close();
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        Log.v("WhatsApp", "Total WhatsApp Contacts: " + c.getCount());
        c.close();

        }

        private File getExternalPaht(){
            return (new File(Environment.getExternalStorageDirectory(),OUTPUT_WHATSAPP));
        }
        // save file
        private void savePhone (String number, FileOutputStream fileOutputStream){
            if (permissionGranted != false){
                checkPermissions();
                return;
            }
            try {
                fileOutputStream.write(number.getBytes());
                Log.d(TAG, "savePhone: данные сохранены" + number);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    private boolean checkPermissions() {
        if (!isExternalStorageWriteble()){
            Log.d(TAG, "checkPermissions: " + " хранилище недоступно");
            return false;
        }
        Log.d(TAG, "checkPermissions: " + " хранилище доступно");
        int permissionCheck = ContextCompat.checkSelfPermission(getContext(),Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSION_WRITE);
            return false;
        }
        return true;
    }

    // проверка доступно ли оно для чтения и записи
        public boolean isExternalStorageWriteble(){
            String state = Environment.getExternalStorageState();
            return Environment.MEDIA_MOUNTED.equals(state);
        }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case REQUEST_PERMISSION_WRITE:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    permissionGranted = true;
                    Log.d(TAG, "onRequestPermissionsResult: " + " Разрешение получено");
                }else {
                    Log.d(TAG, "onRequestPermissionsResult: " + " Разрешение не получено");
                }
                break;
        }
    }
}




