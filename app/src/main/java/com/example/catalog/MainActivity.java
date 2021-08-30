package com.example.catalog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.catalog.adapter.CategoryAdapter;
import com.example.catalog.adapter.CourseAdapter;
import com.example.catalog.model.Category;
import com.example.catalog.model.Course;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView categoryRecycler, courseRecycler;
    CategoryAdapter categoryAdapter;
    static CourseAdapter courseAdapter;
    static List<Course> courseList = new ArrayList<>();
    static List<Course> fullCoursesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category(0,"", "filter"));
        categoryList.add(new Category(1,"Ігри", "category"));
        categoryList.add(new Category(2,"Сайти", "category"));
        categoryList.add(new Category(3,"Мови", "category"));
        categoryList.add(new Category(4,"Решта", "category"));

        setCategoryRecycler(categoryList);

        courseList.add(new Course(1,"java", "Професія Java\nрозробник", "1 січня", "початковий", "#424345","@string/course_desc", 3));
        courseList.add(new Course(2,"python", "Професія Python\nрозробник", "10 січня", "початковий", "#9FA52D", "Test", 3));
        courseList.add(new Course(3,"unity", "Професія Unity\nрозробник", "20 січня", "початковий", "#65173D","Test", 1));
        courseList.add(new Course(4,"full_stack","Професія Full-stack\nрозробник","25 січня","середній","#FEB600", "Test", 2));

        fullCoursesList.addAll(courseList);

        setCourseRecycler(courseList);

    }

    public void openShoppingCard(View view){
        Intent intent = new Intent(this, OrderPage.class);
        startActivity(intent);
    }

    private void setCourseRecycler(List<Course> courseList) {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);

        courseRecycler = findViewById(R.id.courseRecycler);
        courseRecycler.setLayoutManager(layoutManager);

        courseAdapter = new CourseAdapter(this, courseList);
        courseRecycler.setAdapter(courseAdapter);
    }

    private void setCategoryRecycler(List<Category> categoryList) {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);

        categoryRecycler = findViewById(R.id.categoryRecycler);
        categoryRecycler.setLayoutManager(layoutManager);

        categoryAdapter = new CategoryAdapter(this, categoryList);
        categoryRecycler.setAdapter(categoryAdapter);

    }

    public static void showCoursesByCategory(int category){

        courseList.clear();
        courseList.addAll(fullCoursesList);

        if(category != 0) {
            List<Course> filterCourses = new ArrayList<>();

            for (Course c : courseList) {
                if (c.getCategory() == category)
                    filterCourses.add(c);
            }

            courseList.clear();
            courseList.addAll(filterCourses);
        }

        courseAdapter.notifyDataSetChanged();

    }

}