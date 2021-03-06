package com.example.lab67;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.example.lab67.model.Note;
import com.example.lab67.rep.AppDatabase;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.example.lab67.model.Note;
import com.example.lab67.rep.AppDatabase;

public class NoteActivity extends AppCompatActivity {

    private int mNoteId;
    private boolean mEditMode;

    @BindView(R.id.title_et)
    EditText mTitle;

    @BindView(R.id.contents_et)
    EditText mText;

    @BindView(R.id.toolbar)
    MaterialToolbar mToolbar;

    Note mNote;

    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        ButterKnife.bind(this);
        db = AppDatabase.getInstance(this);

        initWindowConfiguration();
        getIntentData();

        mToolbar.setNavigationOnClickListener(view -> {
            finish();
        });

        if (mEditMode) {
            Executors.newSingleThreadExecutor().execute(() -> {
                mNote = db.noteDAO().getNoteById(mNoteId);
                if (mNote != null)
                    runOnUiThread(this::populateViews);
            });
        }
    }

    private void populateViews() {
        mTitle.setText(mNote.getTitle());
        mText.setText(mNote.getContents());
    }

    private void getIntentData() {
        mNoteId = getIntent().getIntExtra(Notepad.NOTE_ID_ARG, -1);
        mEditMode = mNoteId != -1;
    }

    public void initWindowConfiguration() {
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(Color.TRANSPARENT);

        CollapsingToolbarLayout ctb = findViewById(R.id.toolbar_layout);
        ctb.setCollapsedTitleTypeface(ResourcesCompat.getFont(this, R.font.mont));
        ctb.setExpandedTitleTypeface(ResourcesCompat.getFont(this, R.font.mont));
    }

    @OnClick(R.id.create)
    public void createClick() {
        String title = mTitle.getText().toString();
        String text = mText.getText().toString();

        Note note;
        Date now = Calendar.getInstance().getTime();

        if (mEditMode) {
            note = mNote;
            note.setDateUpdate(now);
            note.setTitle(title);
            note.setContents(text);
        } else {
            note = new Note(title, text, now, now);
        }

        Executors.newSingleThreadExecutor().execute(() -> {
            db.noteDAO().insertNote(note);
        });
        finish();
    }

    @OnClick(R.id.clear)
    public void clearClick() {
        mTitle.getText().clear();
        mText.getText().clear();
    }

}
