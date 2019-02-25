package pactogramma.pactogramma;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

public class CheckPhoneNumber {
private Context context;

    public CheckPhoneNumber(Context context) {
        this.context = context;
    }

    public String hasWhatsapp(String contactID) {
        String rowContactId = null;
        boolean hasWhatsApp;

        String[] projection = new String[]{ContactsContract.RawContacts._ID};
        String selection = ContactsContract.Data.CONTACT_ID + " = ? AND account_type IN (?)";
        String[] selectionArgs = new String[]{contactID, "com.whatsapp"};
        Cursor cursor = context.getContentResolver().query(ContactsContract.RawContacts.CONTENT_URI, projection, selection, selectionArgs, null);
        if (cursor != null) {
            hasWhatsApp = cursor.moveToNext();
            if (hasWhatsApp) {
                rowContactId = cursor.getString(0);
            }
            cursor.close();
        }
        return rowContactId;
    }
}
