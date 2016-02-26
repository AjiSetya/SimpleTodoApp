package com.nbs.simpletodoapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.nbs.simpletodoapp.adapter.NoteAdapter;
import com.nbs.simpletodoapp.db.Note;
import com.nbs.simpletodoapp.event.AddNewNoteEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lvNote;
    private FloatingActionButton fab;

    private List<Note> listNotes;
    private NoteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        lvNote = (ListView)findViewById(R.id.lv_note);

        EventBus.getDefault().register(this);

        listNotes = new ArrayList<>();
        listNotes = Note.listAll(Note.class);

        if (listNotes != null && listNotes.size() > 0){
            adapter = new NoteAdapter(MainActivity.this, listNotes);
            lvNote.setAdapter(adapter);
        }else{
            Snackbar.make(lvNote, "No item found", Snackbar.LENGTH_SHORT).show();
        }

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NoteFormActivity.start(MainActivity.this);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Subscribe
    public void onEvent(AddNewNoteEvent newNoteEvent){
        listNotes.add(newNoteEvent.getNote());
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
