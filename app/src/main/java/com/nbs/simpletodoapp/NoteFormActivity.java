package com.nbs.simpletodoapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nbs.simpletodoapp.db.Note;
import com.nbs.simpletodoapp.event.AddNewNoteEvent;

import org.greenrobot.eventbus.EventBus;

public class NoteFormActivity extends AppCompatActivity {
    private EditText edtTitle, edtTask;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_form);

        getSupportActionBar().setTitle("Add New Note/Task");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edtTitle = (EditText)findViewById(R.id.edt_title);
        edtTask = (EditText)findViewById(R.id.edt_task_description);
        btnSubmit = (Button)findViewById(R.id.btn_submit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = edtTitle.getText().toString().trim();
                String task = edtTask.getText().toString().trim();
                if (TextUtils.isEmpty(title) || TextUtils.isEmpty(task)){
                    Toast.makeText(NoteFormActivity.this, "Fields are required", Toast.LENGTH_LONG).show();
                }else{
                    Note note = new Note(Util.getNewId(), title, task, Util.getCurrentDate());
                    note.save();

                    AddNewNoteEvent addNewNoteEvent = new AddNewNoteEvent();
                    addNewNoteEvent.setNote(note);

                    EventBus.getDefault().post(addNewNoteEvent);

                    Toast.makeText(NoteFormActivity.this, "New note saved", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, NoteFormActivity.class);
        context.startActivity(starter);
    }
}
