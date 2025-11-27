package com.lifeos.app.data.local.dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.lifeos.app.data.local.Converters;
import com.lifeos.app.data.local.entity.NoteEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.IllegalStateException;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;
import kotlinx.datetime.Instant;
import kotlinx.datetime.LocalDate;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class NoteDao_Impl implements NoteDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<NoteEntity> __insertionAdapterOfNoteEntity;

  private final Converters __converters = new Converters();

  private final EntityDeletionOrUpdateAdapter<NoteEntity> __updateAdapterOfNoteEntity;

  private final SharedSQLiteStatement __preparedStmtOfDelete;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public NoteDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfNoteEntity = new EntityInsertionAdapter<NoteEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `notes` (`id`,`user_id`,`notebook_id`,`title`,`content`,`is_journal`,`journal_date`,`created_at`,`updated_at`) VALUES (?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final NoteEntity entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getUserId());
        statement.bindString(3, entity.getNotebookId());
        statement.bindString(4, entity.getTitle());
        statement.bindString(5, entity.getContent());
        final int _tmp = entity.isJournal() ? 1 : 0;
        statement.bindLong(6, _tmp);
        final String _tmp_1 = __converters.fromLocalDate(entity.getJournalDate());
        if (_tmp_1 == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, _tmp_1);
        }
        final Long _tmp_2 = __converters.fromInstant(entity.getCreatedAt());
        if (_tmp_2 == null) {
          statement.bindNull(8);
        } else {
          statement.bindLong(8, _tmp_2);
        }
        final Long _tmp_3 = __converters.fromInstant(entity.getUpdatedAt());
        if (_tmp_3 == null) {
          statement.bindNull(9);
        } else {
          statement.bindLong(9, _tmp_3);
        }
      }
    };
    this.__updateAdapterOfNoteEntity = new EntityDeletionOrUpdateAdapter<NoteEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `notes` SET `id` = ?,`user_id` = ?,`notebook_id` = ?,`title` = ?,`content` = ?,`is_journal` = ?,`journal_date` = ?,`created_at` = ?,`updated_at` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final NoteEntity entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getUserId());
        statement.bindString(3, entity.getNotebookId());
        statement.bindString(4, entity.getTitle());
        statement.bindString(5, entity.getContent());
        final int _tmp = entity.isJournal() ? 1 : 0;
        statement.bindLong(6, _tmp);
        final String _tmp_1 = __converters.fromLocalDate(entity.getJournalDate());
        if (_tmp_1 == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, _tmp_1);
        }
        final Long _tmp_2 = __converters.fromInstant(entity.getCreatedAt());
        if (_tmp_2 == null) {
          statement.bindNull(8);
        } else {
          statement.bindLong(8, _tmp_2);
        }
        final Long _tmp_3 = __converters.fromInstant(entity.getUpdatedAt());
        if (_tmp_3 == null) {
          statement.bindNull(9);
        } else {
          statement.bindLong(9, _tmp_3);
        }
        statement.bindString(10, entity.getId());
      }
    };
    this.__preparedStmtOfDelete = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM notes WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM notes WHERE user_id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final NoteEntity note, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfNoteEntity.insert(note);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertAll(final List<NoteEntity> notes,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfNoteEntity.insert(notes);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final NoteEntity note, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfNoteEntity.handle(note);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object delete(final String id, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDelete.acquire();
        int _argIndex = 1;
        _stmt.bindString(_argIndex, id);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDelete.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteAll(final String userId, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
        int _argIndex = 1;
        _stmt.bindString(_argIndex, userId);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteAll.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<NoteEntity>> getNotes(final String userId) {
    final String _sql = "SELECT * FROM notes WHERE user_id = ? ORDER BY created_at DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, userId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"notes"}, new Callable<List<NoteEntity>>() {
      @Override
      @NonNull
      public List<NoteEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "user_id");
          final int _cursorIndexOfNotebookId = CursorUtil.getColumnIndexOrThrow(_cursor, "notebook_id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
          final int _cursorIndexOfIsJournal = CursorUtil.getColumnIndexOrThrow(_cursor, "is_journal");
          final int _cursorIndexOfJournalDate = CursorUtil.getColumnIndexOrThrow(_cursor, "journal_date");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
          final List<NoteEntity> _result = new ArrayList<NoteEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final NoteEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpUserId;
            _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            final String _tmpNotebookId;
            _tmpNotebookId = _cursor.getString(_cursorIndexOfNotebookId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpContent;
            _tmpContent = _cursor.getString(_cursorIndexOfContent);
            final boolean _tmpIsJournal;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsJournal);
            _tmpIsJournal = _tmp != 0;
            final LocalDate _tmpJournalDate;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfJournalDate)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfJournalDate);
            }
            _tmpJournalDate = __converters.toLocalDate(_tmp_1);
            final Instant _tmpCreatedAt;
            final Long _tmp_2;
            if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getLong(_cursorIndexOfCreatedAt);
            }
            final Instant _tmp_3 = __converters.toInstant(_tmp_2);
            if (_tmp_3 == null) {
              throw new IllegalStateException("Expected NON-NULL 'kotlinx.datetime.Instant', but it was NULL.");
            } else {
              _tmpCreatedAt = _tmp_3;
            }
            final Instant _tmpUpdatedAt;
            final Long _tmp_4;
            if (_cursor.isNull(_cursorIndexOfUpdatedAt)) {
              _tmp_4 = null;
            } else {
              _tmp_4 = _cursor.getLong(_cursorIndexOfUpdatedAt);
            }
            final Instant _tmp_5 = __converters.toInstant(_tmp_4);
            if (_tmp_5 == null) {
              throw new IllegalStateException("Expected NON-NULL 'kotlinx.datetime.Instant', but it was NULL.");
            } else {
              _tmpUpdatedAt = _tmp_5;
            }
            _item = new NoteEntity(_tmpId,_tmpUserId,_tmpNotebookId,_tmpTitle,_tmpContent,_tmpIsJournal,_tmpJournalDate,_tmpCreatedAt,_tmpUpdatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<NoteEntity>> getNotesByNotebook(final String notebookId) {
    final String _sql = "SELECT * FROM notes WHERE notebook_id = ? ORDER BY created_at DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, notebookId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"notes"}, new Callable<List<NoteEntity>>() {
      @Override
      @NonNull
      public List<NoteEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "user_id");
          final int _cursorIndexOfNotebookId = CursorUtil.getColumnIndexOrThrow(_cursor, "notebook_id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
          final int _cursorIndexOfIsJournal = CursorUtil.getColumnIndexOrThrow(_cursor, "is_journal");
          final int _cursorIndexOfJournalDate = CursorUtil.getColumnIndexOrThrow(_cursor, "journal_date");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
          final List<NoteEntity> _result = new ArrayList<NoteEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final NoteEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpUserId;
            _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            final String _tmpNotebookId;
            _tmpNotebookId = _cursor.getString(_cursorIndexOfNotebookId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpContent;
            _tmpContent = _cursor.getString(_cursorIndexOfContent);
            final boolean _tmpIsJournal;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsJournal);
            _tmpIsJournal = _tmp != 0;
            final LocalDate _tmpJournalDate;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfJournalDate)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfJournalDate);
            }
            _tmpJournalDate = __converters.toLocalDate(_tmp_1);
            final Instant _tmpCreatedAt;
            final Long _tmp_2;
            if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getLong(_cursorIndexOfCreatedAt);
            }
            final Instant _tmp_3 = __converters.toInstant(_tmp_2);
            if (_tmp_3 == null) {
              throw new IllegalStateException("Expected NON-NULL 'kotlinx.datetime.Instant', but it was NULL.");
            } else {
              _tmpCreatedAt = _tmp_3;
            }
            final Instant _tmpUpdatedAt;
            final Long _tmp_4;
            if (_cursor.isNull(_cursorIndexOfUpdatedAt)) {
              _tmp_4 = null;
            } else {
              _tmp_4 = _cursor.getLong(_cursorIndexOfUpdatedAt);
            }
            final Instant _tmp_5 = __converters.toInstant(_tmp_4);
            if (_tmp_5 == null) {
              throw new IllegalStateException("Expected NON-NULL 'kotlinx.datetime.Instant', but it was NULL.");
            } else {
              _tmpUpdatedAt = _tmp_5;
            }
            _item = new NoteEntity(_tmpId,_tmpUserId,_tmpNotebookId,_tmpTitle,_tmpContent,_tmpIsJournal,_tmpJournalDate,_tmpCreatedAt,_tmpUpdatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<NoteEntity> getNote(final String id) {
    final String _sql = "SELECT * FROM notes WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, id);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"notes"}, new Callable<NoteEntity>() {
      @Override
      @Nullable
      public NoteEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "user_id");
          final int _cursorIndexOfNotebookId = CursorUtil.getColumnIndexOrThrow(_cursor, "notebook_id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
          final int _cursorIndexOfIsJournal = CursorUtil.getColumnIndexOrThrow(_cursor, "is_journal");
          final int _cursorIndexOfJournalDate = CursorUtil.getColumnIndexOrThrow(_cursor, "journal_date");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
          final NoteEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpUserId;
            _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            final String _tmpNotebookId;
            _tmpNotebookId = _cursor.getString(_cursorIndexOfNotebookId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpContent;
            _tmpContent = _cursor.getString(_cursorIndexOfContent);
            final boolean _tmpIsJournal;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsJournal);
            _tmpIsJournal = _tmp != 0;
            final LocalDate _tmpJournalDate;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfJournalDate)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfJournalDate);
            }
            _tmpJournalDate = __converters.toLocalDate(_tmp_1);
            final Instant _tmpCreatedAt;
            final Long _tmp_2;
            if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getLong(_cursorIndexOfCreatedAt);
            }
            final Instant _tmp_3 = __converters.toInstant(_tmp_2);
            if (_tmp_3 == null) {
              throw new IllegalStateException("Expected NON-NULL 'kotlinx.datetime.Instant', but it was NULL.");
            } else {
              _tmpCreatedAt = _tmp_3;
            }
            final Instant _tmpUpdatedAt;
            final Long _tmp_4;
            if (_cursor.isNull(_cursorIndexOfUpdatedAt)) {
              _tmp_4 = null;
            } else {
              _tmp_4 = _cursor.getLong(_cursorIndexOfUpdatedAt);
            }
            final Instant _tmp_5 = __converters.toInstant(_tmp_4);
            if (_tmp_5 == null) {
              throw new IllegalStateException("Expected NON-NULL 'kotlinx.datetime.Instant', but it was NULL.");
            } else {
              _tmpUpdatedAt = _tmp_5;
            }
            _result = new NoteEntity(_tmpId,_tmpUserId,_tmpNotebookId,_tmpTitle,_tmpContent,_tmpIsJournal,_tmpJournalDate,_tmpCreatedAt,_tmpUpdatedAt);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<NoteEntity> getJournalNote(final String notebookId, final String date) {
    final String _sql = "SELECT * FROM notes WHERE notebook_id = ? AND is_journal = 1 AND journal_date = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindString(_argIndex, notebookId);
    _argIndex = 2;
    _statement.bindString(_argIndex, date);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"notes"}, new Callable<NoteEntity>() {
      @Override
      @Nullable
      public NoteEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "user_id");
          final int _cursorIndexOfNotebookId = CursorUtil.getColumnIndexOrThrow(_cursor, "notebook_id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
          final int _cursorIndexOfIsJournal = CursorUtil.getColumnIndexOrThrow(_cursor, "is_journal");
          final int _cursorIndexOfJournalDate = CursorUtil.getColumnIndexOrThrow(_cursor, "journal_date");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
          final NoteEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpUserId;
            _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            final String _tmpNotebookId;
            _tmpNotebookId = _cursor.getString(_cursorIndexOfNotebookId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpContent;
            _tmpContent = _cursor.getString(_cursorIndexOfContent);
            final boolean _tmpIsJournal;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsJournal);
            _tmpIsJournal = _tmp != 0;
            final LocalDate _tmpJournalDate;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfJournalDate)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfJournalDate);
            }
            _tmpJournalDate = __converters.toLocalDate(_tmp_1);
            final Instant _tmpCreatedAt;
            final Long _tmp_2;
            if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getLong(_cursorIndexOfCreatedAt);
            }
            final Instant _tmp_3 = __converters.toInstant(_tmp_2);
            if (_tmp_3 == null) {
              throw new IllegalStateException("Expected NON-NULL 'kotlinx.datetime.Instant', but it was NULL.");
            } else {
              _tmpCreatedAt = _tmp_3;
            }
            final Instant _tmpUpdatedAt;
            final Long _tmp_4;
            if (_cursor.isNull(_cursorIndexOfUpdatedAt)) {
              _tmp_4 = null;
            } else {
              _tmp_4 = _cursor.getLong(_cursorIndexOfUpdatedAt);
            }
            final Instant _tmp_5 = __converters.toInstant(_tmp_4);
            if (_tmp_5 == null) {
              throw new IllegalStateException("Expected NON-NULL 'kotlinx.datetime.Instant', but it was NULL.");
            } else {
              _tmpUpdatedAt = _tmp_5;
            }
            _result = new NoteEntity(_tmpId,_tmpUserId,_tmpNotebookId,_tmpTitle,_tmpContent,_tmpIsJournal,_tmpJournalDate,_tmpCreatedAt,_tmpUpdatedAt);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
