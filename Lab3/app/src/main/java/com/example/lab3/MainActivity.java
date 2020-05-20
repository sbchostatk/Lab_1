package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button b1;
    Button b2;
    Button b3;

    SQLiteDatabase database;
    Database db;

    ArrayList<String> names;
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b1 = (Button) findViewById(R.id.button1);
        Button b2 = (Button) findViewById(R.id.button2);
        Button b3 = (Button) findViewById(R.id.button3);

        db = new Database(this);
        database = db.getWritableDatabase();
        database.execSQL("DELETE FROM students");
        names = new ArrayList<>();

        info();

        db.close();

        b1.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this, Show_Database.class);
                startActivity(intent);
            }
        });

        b2.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                database = db.getWritableDatabase();

                Random random = new Random();
                int number = random.nextInt(names.size());
                Date today = new Date();
                String time = sdf.format(today);

                database.execSQL("INSERT INTO students(name, time) VALUES (\'" + names.get(number)+ "\','" + time + "');");
                names.remove(number);

                db.close();

                Toast.makeText(getApplicationContext(), "Запись добавлена", Toast.LENGTH_SHORT).show();
            }
        });

        b3.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                database = db.getWritableDatabase();

                database.execSQL("UPDATE students SET name = 'Иванов Иван Иванович' WHERE _id = (SELECT MAX(_id) FROM students)");

                db.close();

                Toast.makeText(getApplicationContext(), "Последняя запись изменена", Toast.LENGTH_SHORT).show();
            }
        });
    }

    void info()
    {
        names.add("Корнилов Лев Макарович");
        names.add("Александров Гордей Егорович");
        names.add("Фомин Геннадий Донатович");
        names.add("Белоусов Георгий Федорович");
        names.add("Пономарёв Вадим Федосеевич");
        names.add("Федосеев Юлиан Денисович");
        names.add("Максимов Кирилл Романович");
        names.add("Стрелков Корней Львович");
        names.add("Маслов Артем Ярославович");
        names.add("Бобылёв Давид Федорович");
        names.add("Устинова Роза Яковлевна");
        names.add("Котова Рая Анатольевна");
        names.add("Федотова Наталья Арсеньевна");
        names.add("Потапова Вероника Валентиновна");
        names.add("Терентьева Ангелина Тарасовна");
        names.add("Никитина Ксения Лукьяновна");
        names.add("Матвеева Евгения Богдановна");
        names.add("Дроздова Марта Романовна");
        names.add("Егорова Дарья Владиславовна");
        names.add("Мельникова Евгения Григорьевна");

        Random random = new Random();
        int number;

        for (int i = 0; i < 5; i++) {

            number = random.nextInt(names.size());

            Date today = new Date();
            String time = sdf.format(today);

            database.execSQL("INSERT INTO students(name, time) VALUES (\'" + names.get(number) + "\',\'" + time + "\');");
            names.remove(number);
        }
    }
}
