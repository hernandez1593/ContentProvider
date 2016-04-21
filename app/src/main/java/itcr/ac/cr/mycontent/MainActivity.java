package itcr.ac.cr.mycontent;

import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {



    Button button;
    TextView txtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button)findViewById(R.id.button);
        txtView = (TextView)findViewById(R.id.txtv1);

        button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.button:
                ObtenerDatos();

                break;
            default:
                break;
        }
    }

    public void ObtenerDatos(){
        String[] projeccion = new String[] {
                ContactsContract.Data._ID,
                ContactsContract.Data.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER,
                ContactsContract.CommonDataKinds.Phone.TYPE
        };
        String selectionClause = ContactsContract.Data.MIMETYPE + "='"+
                ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE+ "' AND "
                + ContactsContract.CommonDataKinds.Phone.NUMBER+ " IS NOT NULL";

        String sortOrder = ContactsContract.Data.DISPLAY_NAME + " ASC";

        Cursor c = getContentResolver().query(
                ContactsContract.Data.CONTENT_URI,
                projeccion,
                selectionClause,
                null,
                sortOrder
        );

        txtView.setText("");
        while (c.moveToNext()){
            txtView.append("Identificador: " +
                            c.getString(0) + "Nombres:" +
                            c.getString(1) + "Numero: " +
                            c.getString(2) + "Tipo: " +
                            c.getString(3) + "\n"
            );

        }
        c.close();

    }
}
