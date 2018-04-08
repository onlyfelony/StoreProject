package com.example.administrator.storeproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private MyDatabaseHelper myDatabaseHelper;
    private Button createDB,outtosd,addbookda,querybook;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDatabaseHelper = new MyDatabaseHelper(this,"xxx.db",null,1);
        createDB = findViewById(R.id.add_db);
        outtosd = findViewById(R.id.to_SD);
        addbookda = findViewById(R.id.add_bookData);
        querybook =findViewById(R.id.search_data);

        createDB.setOnClickListener(this);
        outtosd.setOnClickListener(this);
        addbookda.setOnClickListener(this);
        querybook.setOnClickListener(this);
/*        outtosd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //数据库文件会存放在/data/data/<package name>/databases/目录下

               // boolean x=SdUtils.pathSaveSD("/data/data/com.example.administrator.storeproject/databases/BookstoreDB.db","t123.db", "xyq");
            }
        });*/

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_db:
                LitePal.getDatabase();//创建数据库和表
                break;

            case R.id.to_SD:
                //数据库文件会存放在/data/data/<package name>/databases/目录下
               // boolean x =SdUtils.IsExist();
                boolean x=SdUtils.pathSaveSD("/data/data/com.example.administrator.storeproject/databases/BookstoreDB.db","mtest.db", "zyj");
              Toast.makeText(MainActivity.this,"转到SD的结果是"+x,Toast.LENGTH_SHORT).show();
                break;

            case R.id.add_bookData:
                Book book = new Book();
                book.setAuthor("Yu Guo");
                book.setId(100);
                book.setName("Pari Yuan");
                book.setPages(501);
                book.setPrice(37.03);

                book.save();
                myDatabaseHelper.getWritableDatabase();
                break;

            case R.id.search_data:
                List<Book> books = DataSupport.where("pages > ?","400").find(Book.class);

                for (Book book1:books)
                    Toast.makeText(MainActivity.this,book1.getName(),Toast.LENGTH_SHORT).show();

                break;
             default:
                 break;



        }
    }
}
