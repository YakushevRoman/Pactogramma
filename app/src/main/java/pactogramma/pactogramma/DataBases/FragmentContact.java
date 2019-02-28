package pactogramma.pactogramma.DataBases;

import android.Manifest;

import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentResolver;


import android.content.Intent;
import android.content.OperationApplicationException;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
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
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import pactogramma.pactogramma.R;

public class FragmentContact extends Fragment {
    public static final String TAG = "WhatsApp";
    private static final int REQUEST_CODE_READ_CONTACTS = 1;
    private static boolean READ_CONTACTS_GRANTED = false;
    private Object object;
    ListView contactList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        object = new Object();
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);
        contactList = view.findViewById(R.id.contactList);
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
                    writeContact();
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
                    whatsappload();
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

    private synchronized void whatsappload(){
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
        sendIntent.setType("text/plain");
        sendIntent.setPackage("com.whatsapp");
        startActivity(sendIntent);
    }

    private synchronized void   writeContact() {
        // для добавления контакта в базу телефона
        ArrayList<ContentProviderOperation> ops = new ArrayList<>();
        int rawContactInsertIndex = ops.size();

        ops.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
                .build());
        ops.add(ContentProviderOperation
                .newInsert(Data.CONTENT_URI)
                .withValueBackReference(Data.RAW_CONTACT_ID,rawContactInsertIndex)
                .withValue(Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, "WWWElectro4") // Name of the person
                .build());
        ops.add(ContentProviderOperation
                .newInsert(Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID,   rawContactInsertIndex)
                .withValue(Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, "+380631348299") // Number of the person
                .withValue(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)
                .build()); // Type of mobile number
        try {
            ContentProviderResult[] res = Objects.requireNonNull(getActivity()).getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
        }
        catch (RemoteException | OperationApplicationException e) {
            // error
        }
    }

    private synchronized void loadContacts() throws OperationApplicationException, RemoteException {
        final String[] projection = {
                Data.DATA3
        };

        final String selection = Data.MIMETYPE + " =? and account_type=?";
        final String[] selectionArgs = {
                "vnd.android.cursor.item/vnd.com.whatsapp.profile",
                "com.whatsapp"
        };
        ContentResolver cr = Objects.requireNonNull(getActivity()).getContentResolver();
        Cursor c = cr.query(
                Data.CONTENT_URI,
                projection,
                selection,
                selectionArgs,
                null);
        while (c.moveToNext()) {
            String number = c.getString(0);
            /*String id = c.getString(c.getColumnIndex(Data.CONTACT_ID));
            String number = c.getString(c.getColumnIndex(Data.DATA3));
            String name = "";
            //для нахождения имен контактов
            Cursor mCursor = getActivity().getContentResolver().query(
                    ContactsContract.Contacts.CONTENT_URI,
                    new String[]{ContactsContract.Contacts.DISPLAY_NAME},
                    ContactsContract.Contacts._ID + " =?",
                    new String[]{id},
                    null);
            while (mCursor.moveToNext()) {
                name = mCursor.getString(mCursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

            }*/
            Log.d("WhatsApp",  number);
            //mCursor.close();
        }
        Log.v("WhatsApp", "Total WhatsApp Contacts: " + c.getCount());
        c.close();

        }

}




