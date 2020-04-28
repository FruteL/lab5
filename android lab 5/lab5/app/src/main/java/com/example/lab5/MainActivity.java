package com.example.lab5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.shapes.Shape;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Кол-во элементов
        int N = 50;
        List<Circle> l = new ArrayList<>();
        //Создание случайных объектов
        for (int i = 0; i < N; i++){
            l.add(new Circle((int)(Math.random()*100), (int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255)));
        }

        //Задание сетки
        RecyclerView rv = findViewById(R.id.grid);
        rv.setHasFixedSize(true);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 4);
        rv.setLayoutManager(layoutManager);

        rv.setAdapter(new MyAdapter(l, this, getSupportFragmentManager()));

    }
}

//адаптер для списка
class MyAdapter extends RecyclerView.Adapter<MyAdapter.CircleViewHolder> {

    private List<Circle> list;
    private Context context;
    FragmentManager manager;

    public MyAdapter(List<Circle> list, Context context, FragmentManager manager) {
        this.list = list;
        this.context = context;
        this.manager = manager;
    }

    @NonNull
    @Override
    public CircleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);
        CircleViewHolder holder = new CircleViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CircleViewHolder holder, int position) {
        //Передача параметров
        holder.text.setText(String.valueOf(list.get(position).getNumber()));
        ((GradientDrawable)holder.text.getBackground()).setColor(list.get(position).getColor());
        holder.text.setOnClickListener(v->{
            MyDialoge dialog = MyDialoge.newInstance(String.valueOf(list.get(position).getNumber()));
            dialog.show(manager, "dlg");
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class CircleViewHolder extends RecyclerView.ViewHolder {
        public TextView text;

        public CircleViewHolder(@NonNull View view) {
            super(view);

            text = view.findViewById(R.id.textView);
        }
    }
}

class Circle {
    private int number;
    private int color;
    private Drawable drawable;

    public Circle(int number, int f, int s, int t) {
        this.number = number;
        this.color = Color.rgb(f, s, t);

    }

    public int getNumber() {
        return number;
    }

    public int getColor() {
        return color;
    }
}
