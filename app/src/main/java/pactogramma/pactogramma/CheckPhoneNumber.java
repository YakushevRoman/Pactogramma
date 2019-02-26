package pactogramma.pactogramma;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.util.Log;

import java.util.ArrayList;

public class CheckPhoneNumber {
    public static final String TAG = "phone";
private Context context;

    public CheckPhoneNumber(Context context) {
        this.context = context;
    }

    public String hasWhatsapp() {
        String rowContactId = null;
        boolean hasWhatsApp = false;
        ArrayList<String> strings = new ArrayList<>();
        int whatsAppExists = 0;
        //boolean hasWhatsApp;

       /* String[] projection = new String[]{ContactsContract.RawContacts._ID};
        String selection = ContactsContract.Data.CONTACT_ID + " = ? AND account_type IN (?)";
        String[] selectionArgs = new String[]{contactID, "com.whatsapp"};
        Cursor cursor = getActivity().getContentResolver().query(ContactsContract.RawContacts.CONTENT_URI, projection, selection, selectionArgs, null);
        if (cursor != null) {
            hasWhatsApp = cursor.moveToNext();
            if (hasWhatsApp) {
                whatsAppExists = 1;
            }
            cursor.close();
        }
        cursor.close();
        Log.d(TAG, "hasWhatsapp: " + strings);*/
        return rowContactId;
    }
}
