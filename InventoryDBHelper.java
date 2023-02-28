package com.example.inventoryapp;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class InventoryDBHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;

    public static final String DBNAME = "Inventory.db";
    public static final String TABLE_ITEMS = "items";
    private static final String COLUMN_ID = "STOCK_NUMBER";
    private static final String COLUMN_NAME = "GEN_NAME";
    private static final String ITEM_LOC = "ITEM_LOC";
    private static final String ITEM_COUNT = "ITEM_COUNT";


    public InventoryDBHelper(Context context) {
        super(context, DBNAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase ItemsDB) {
        String CREATE_ITEMS_TABLE = "CREATE TABLE " +
                TABLE_ITEMS + "(" + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_NAME
                + " TEXT," + ITEM_LOC + " TEXT," + ITEM_COUNT + " TEXT " + ")";
        ItemsDB.execSQL(CREATE_ITEMS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase ItemsDB,  int oldVersion, int newVersion) {
        ItemsDB.execSQL("DROP TABLE IF EXISTS " +  TABLE_ITEMS);
        onCreate(ItemsDB);

    }



    public Boolean insertData(String STOCK_NUMBER, String GEN_NAME, String ITEM_LOC, int ITEM_COUNT){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("STOCK_NUMBER", STOCK_NUMBER);
        contentValues.put("GEN_NAME", GEN_NAME);
        contentValues.put("ITEM_LOC", ITEM_LOC);
        contentValues.put("ITEM_COUNT", ITEM_COUNT);
        long result = MyDB.insert("ITEMS", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    public Boolean checkStockNum(String STOCK_NUMBER) {
        SQLiteDatabase ItemsDB = this.getWritableDatabase();
        Cursor cursor = ItemsDB.rawQuery("Select * from items where STOCK_NUMBER = ?", new String[]{STOCK_NUMBER});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }


    String loadHandler() {
        String result = "";
        String query = "Select*FROM " + TABLE_ITEMS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            int result_0 = cursor.getInt(0);
            String result_1 = cursor.getString(1);
            result += String.valueOf(result_0) + " " + result_1 +
                    System.getProperty("line.separator");
        }
        cursor.close();
        db.close();
        if(result.equals(""))
            result="No Record Found";
        return result;
    }


    long addHandler(Items items) {
        long id;
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, items.getID());
        values.put(COLUMN_NAME, items.getItemName());
        values.put(ITEM_LOC, items.getItemLoc());
        values.put(ITEM_COUNT, items.getItemLoc());

        SQLiteDatabase db = this.getWritableDatabase();
        id = db.insert(TABLE_ITEMS, null, values);
        db.close();
        return id;
    }


    boolean updateHandler(int ID, String name, String loc, int num ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(COLUMN_ID, ID);
        args.put(COLUMN_NAME, name);
        args.put(ITEM_LOC, loc);
        args.put(ITEM_COUNT, num);
        return db.update(TABLE_ITEMS, args, COLUMN_ID + "=" + ID, null) > 0;
    }
    boolean deleteHandler(int ID) {
        boolean result = false;
        String query = "Select*FROM " + TABLE_ITEMS + " WHERE " + COLUMN_ID + " = '" + String.valueOf(ID) + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Items student = new Items();
        if (cursor.moveToFirst()) {
            student.setID(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_ITEMS, COLUMN_ID + "=?",
                    new String[] {
                            String.valueOf(student.getID())
                    });
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

}