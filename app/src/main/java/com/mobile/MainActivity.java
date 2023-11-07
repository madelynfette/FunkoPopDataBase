package com.mobile;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
    Button insertButton;
    Button queryButton;
    Button updateButton;
    Button deleteButton;
    Button nextButton;
    Button previousButton;
    EditText name;
    EditText number;
    EditText type;
    EditText fandom;
    EditText on;
    EditText ultimate;
    EditText price;
    TextView idTv;
    TextView namein;
    TextView numberin;
    TextView typein;
    TextView fandomin;
    TextView onin;
    TextView ultimatein;
    TextView pricein;

    Cursor mCursor;

    View.OnClickListener updateListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ContentValues mUpdateValues = new ContentValues();

            mUpdateValues.put(PopDBProvider.FIRSTCOLUMN, name.getText().toString().trim());
            mUpdateValues.put(PopDBProvider.SECONDCOLUMN, number.getText().toString().trim());
            mUpdateValues.put(PopDBProvider.THIRDCOLUMN, type.getText().toString().trim());
            mUpdateValues.put(PopDBProvider.FOURTHCOLUMN, fandom.getText().toString().trim());
            mUpdateValues.put(PopDBProvider.FIFTHCOLUMN, on.getText().toString().trim());
            mUpdateValues.put(PopDBProvider.SIXTHCOLUMN, ultimate.getText().toString().trim());
            mUpdateValues.put(PopDBProvider.SEVENTHCOLUMN, price.getText().toString().trim());


            String mSelectionClause = PopDBProvider.FIRSTCOLUMN + " = ? " + " AND " +
                    PopDBProvider.SECONDCOLUMN + " = ? " + " AND " + PopDBProvider.THIRDCOLUMN + " = ? " + " AND " +
                    PopDBProvider.FOURTHCOLUMN + " = ? " + " AND " + PopDBProvider.FIFTHCOLUMN + " = ? " + " AND " +
                    PopDBProvider.SIXTHCOLUMN + " = ? " + " AND " + PopDBProvider.SEVENTHCOLUMN + " = ? ";

            String[] mSelectionArgs = {
                    namein.getText().toString().trim(),
                    numberin.getText().toString().trim(),
                    typein.getText().toString().trim(),
                    fandomin.getText().toString().trim(),
                    onin.getText().toString().trim(),
                    ultimatein.getText().toString().trim(),
                    pricein.getText().toString().trim()};

            int numRowsUpdates= getContentResolver().update(PopDBProvider.CONTENT_URI, mUpdateValues,
                    mSelectionClause, mSelectionArgs);

            clear();
        }
    };


    View.OnClickListener deleteListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String mSelectionClause = PopDBProvider.FIRSTCOLUMN + " = ? " + " AND " +
                     PopDBProvider.SECONDCOLUMN + " = ? " + " AND " + PopDBProvider.THIRDCOLUMN + " = ? " + " AND " +
                     PopDBProvider.FOURTHCOLUMN + " = ? " + " AND " + PopDBProvider.FIFTHCOLUMN + " = ? " + " AND " +
                     PopDBProvider.SIXTHCOLUMN + " = ? " + " AND " + PopDBProvider.SEVENTHCOLUMN + " = ? ";

            String[] mSelectionArgs = {
                    namein.getText().toString().trim(),
                    numberin.getText().toString().trim(),
                    typein.getText().toString().trim(),
                    fandomin.getText().toString().trim(),
                    onin.getText().toString().trim(),
                    ultimatein.getText().toString().trim(),
                    pricein.getText().toString().trim()};

            int mRowsDeleted = getContentResolver().delete(PopDBProvider.CONTENT_URI, mSelectionClause,
                    mSelectionArgs);



            clear();
        }
    };

    View.OnClickListener insertListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            ContentValues mNewValues = new ContentValues();

            mNewValues.put(PopDBProvider.FIRSTCOLUMN, name.getText().toString());
            mNewValues.put(PopDBProvider.SECONDCOLUMN, number.getText().toString());
            mNewValues.put(PopDBProvider.THIRDCOLUMN, type.getText().toString());
            mNewValues.put(PopDBProvider.FOURTHCOLUMN, fandom.getText().toString());
            mNewValues.put(PopDBProvider.FIFTHCOLUMN, on.getText().toString());
            mNewValues.put(PopDBProvider.SIXTHCOLUMN, ultimate.getText().toString());
            mNewValues.put(PopDBProvider.SEVENTHCOLUMN, price.getText().toString());


            getContentResolver().insert(PopDBProvider.CONTENT_URI, mNewValues);

            clear();
        }
    };

    View.OnClickListener queryListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mCursor = getContentResolver().query(PopDBProvider.CONTENT_URI, null, null, null, null);

            if (mCursor != null) {
                if (mCursor.getCount() > 0) {
                    mCursor.moveToNext();
                    setViews();
                }
            }
        }
    };

    View.OnClickListener previousListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mCursor != null) {
                if (!mCursor.moveToPrevious()) {
                    mCursor.moveToLast();
                }

                setViews();
            }
        }
    };

    View.OnClickListener nextListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mCursor != null) {
                if (!mCursor.moveToNext()) {
                    mCursor.moveToFirst();
                }
                setViews();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.nameinput);
        number = findViewById(R.id.numberinput);
        type = findViewById(R.id.typeinput);
        fandom = findViewById(R.id.fandominput);
        on = findViewById(R.id.oninput);
        ultimate = findViewById(R.id.ultimateinput);
        price = findViewById(R.id.priceinput);


        insertButton = findViewById(R.id.insertButton);
        queryButton = findViewById(R.id.queryButton);
        updateButton = findViewById(R.id.updateButton);
        deleteButton = findViewById(R.id.deleteButton);

        idTv = findViewById(R.id.unique_id);
        namein = findViewById(R.id.nameTV);
        numberin = findViewById(R.id.numberTV);
        typein = findViewById(R.id.typeTV);
        fandomin = findViewById(R.id.fandomTV);
        onin = findViewById(R.id.onTV);
        ultimatein = findViewById(R.id.ultimateTV);
        pricein = findViewById(R.id.priceTV);




        nextButton = findViewById(R.id.nextButton);
        previousButton = findViewById(R.id.previousButton);

        insertButton.setOnClickListener(insertListener);

        updateButton.setOnClickListener(updateListener);

        deleteButton.setOnClickListener(deleteListener);

        queryButton.setOnClickListener(queryListener);

        previousButton.setOnClickListener(previousListener);

        nextButton.setOnClickListener(nextListener);
    }

    public void setViews() {
        idTv.setText(mCursor.getString(0));
        namein.setText(mCursor.getString(1));
        numberin.setText(mCursor.getString(2));
        typein.setText(mCursor.getString(3));
        fandomin.setText(mCursor.getString(4));
        onin.setText(mCursor.getString(5));
        ultimatein.setText(mCursor.getString(6));
        pricein.setText(mCursor.getString(7));

    }

    public void clear() {
        name.setText("");
        number.setText("");
        type.setText("");
        fandom.setText("");
        on.setText("");
        ultimate.setText("");
        price.setText("");


        idTv.setText("");
        namein.setText("");
        numberin.setText("");
        typein.setText("");
        fandomin.setText("");
        onin.setText("");
        ultimatein.setText("");
        pricein.setText("");

        mCursor = null;
    }
}
