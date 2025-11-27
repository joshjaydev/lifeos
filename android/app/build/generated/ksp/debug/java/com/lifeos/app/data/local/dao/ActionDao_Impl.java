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
import com.lifeos.app.data.local.entity.ActionEntity;
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
import kotlinx.datetime.LocalTime;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class ActionDao_Impl implements ActionDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ActionEntity> __insertionAdapterOfActionEntity;

  private final Converters __converters = new Converters();

  private final EntityDeletionOrUpdateAdapter<ActionEntity> __updateAdapterOfActionEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteByFolder;

  private final SharedSQLiteStatement __preparedStmtOfDelete;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public ActionDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfActionEntity = new EntityInsertionAdapter<ActionEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `actions` (`id`,`user_id`,`folder_id`,`title`,`due_date`,`due_time`,`is_recurring`,`recurrence_type`,`notify_before`,`created_at`,`updated_at`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ActionEntity entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getUserId());
        statement.bindString(3, entity.getFolderId());
        statement.bindString(4, entity.getTitle());
        final Long _tmp = __converters.fromInstant(entity.getDueDate());
        if (_tmp == null) {
          statement.bindNull(5);
        } else {
          statement.bindLong(5, _tmp);
        }
        final String _tmp_1 = __converters.fromLocalTime(entity.getDueTime());
        if (_tmp_1 == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, _tmp_1);
        }
        final int _tmp_2 = entity.isRecurring() ? 1 : 0;
        statement.bindLong(7, _tmp_2);
        if (entity.getRecurrenceType() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getRecurrenceType());
        }
        statement.bindLong(9, entity.getNotifyBefore());
        final Long _tmp_3 = __converters.fromInstant(entity.getCreatedAt());
        if (_tmp_3 == null) {
          statement.bindNull(10);
        } else {
          statement.bindLong(10, _tmp_3);
        }
        final Long _tmp_4 = __converters.fromInstant(entity.getUpdatedAt());
        if (_tmp_4 == null) {
          statement.bindNull(11);
        } else {
          statement.bindLong(11, _tmp_4);
        }
      }
    };
    this.__updateAdapterOfActionEntity = new EntityDeletionOrUpdateAdapter<ActionEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `actions` SET `id` = ?,`user_id` = ?,`folder_id` = ?,`title` = ?,`due_date` = ?,`due_time` = ?,`is_recurring` = ?,`recurrence_type` = ?,`notify_before` = ?,`created_at` = ?,`updated_at` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ActionEntity entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getUserId());
        statement.bindString(3, entity.getFolderId());
        statement.bindString(4, entity.getTitle());
        final Long _tmp = __converters.fromInstant(entity.getDueDate());
        if (_tmp == null) {
          statement.bindNull(5);
        } else {
          statement.bindLong(5, _tmp);
        }
        final String _tmp_1 = __converters.fromLocalTime(entity.getDueTime());
        if (_tmp_1 == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, _tmp_1);
        }
        final int _tmp_2 = entity.isRecurring() ? 1 : 0;
        statement.bindLong(7, _tmp_2);
        if (entity.getRecurrenceType() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getRecurrenceType());
        }
        statement.bindLong(9, entity.getNotifyBefore());
        final Long _tmp_3 = __converters.fromInstant(entity.getCreatedAt());
        if (_tmp_3 == null) {
          statement.bindNull(10);
        } else {
          statement.bindLong(10, _tmp_3);
        }
        final Long _tmp_4 = __converters.fromInstant(entity.getUpdatedAt());
        if (_tmp_4 == null) {
          statement.bindNull(11);
        } else {
          statement.bindLong(11, _tmp_4);
        }
        statement.bindString(12, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteByFolder = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM actions WHERE folder_id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDelete = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM actions WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM actions WHERE user_id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final ActionEntity action, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfActionEntity.insert(action);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertAll(final List<ActionEntity> actions,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfActionEntity.insert(actions);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final ActionEntity action, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfActionEntity.handle(action);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteByFolder(final String folderId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteByFolder.acquire();
        int _argIndex = 1;
        _stmt.bindString(_argIndex, folderId);
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
          __preparedStmtOfDeleteByFolder.release(_stmt);
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
  public Flow<List<ActionEntity>> getActions(final String userId) {
    final String _sql = "SELECT * FROM actions WHERE user_id = ? ORDER BY due_date ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, userId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"actions"}, new Callable<List<ActionEntity>>() {
      @Override
      @NonNull
      public List<ActionEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "user_id");
          final int _cursorIndexOfFolderId = CursorUtil.getColumnIndexOrThrow(_cursor, "folder_id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDueDate = CursorUtil.getColumnIndexOrThrow(_cursor, "due_date");
          final int _cursorIndexOfDueTime = CursorUtil.getColumnIndexOrThrow(_cursor, "due_time");
          final int _cursorIndexOfIsRecurring = CursorUtil.getColumnIndexOrThrow(_cursor, "is_recurring");
          final int _cursorIndexOfRecurrenceType = CursorUtil.getColumnIndexOrThrow(_cursor, "recurrence_type");
          final int _cursorIndexOfNotifyBefore = CursorUtil.getColumnIndexOrThrow(_cursor, "notify_before");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
          final List<ActionEntity> _result = new ArrayList<ActionEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ActionEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpUserId;
            _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            final String _tmpFolderId;
            _tmpFolderId = _cursor.getString(_cursorIndexOfFolderId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final Instant _tmpDueDate;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfDueDate)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfDueDate);
            }
            _tmpDueDate = __converters.toInstant(_tmp);
            final LocalTime _tmpDueTime;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfDueTime)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfDueTime);
            }
            _tmpDueTime = __converters.toLocalTime(_tmp_1);
            final boolean _tmpIsRecurring;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsRecurring);
            _tmpIsRecurring = _tmp_2 != 0;
            final String _tmpRecurrenceType;
            if (_cursor.isNull(_cursorIndexOfRecurrenceType)) {
              _tmpRecurrenceType = null;
            } else {
              _tmpRecurrenceType = _cursor.getString(_cursorIndexOfRecurrenceType);
            }
            final int _tmpNotifyBefore;
            _tmpNotifyBefore = _cursor.getInt(_cursorIndexOfNotifyBefore);
            final Instant _tmpCreatedAt;
            final Long _tmp_3;
            if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
              _tmp_3 = null;
            } else {
              _tmp_3 = _cursor.getLong(_cursorIndexOfCreatedAt);
            }
            final Instant _tmp_4 = __converters.toInstant(_tmp_3);
            if (_tmp_4 == null) {
              throw new IllegalStateException("Expected NON-NULL 'kotlinx.datetime.Instant', but it was NULL.");
            } else {
              _tmpCreatedAt = _tmp_4;
            }
            final Instant _tmpUpdatedAt;
            final Long _tmp_5;
            if (_cursor.isNull(_cursorIndexOfUpdatedAt)) {
              _tmp_5 = null;
            } else {
              _tmp_5 = _cursor.getLong(_cursorIndexOfUpdatedAt);
            }
            final Instant _tmp_6 = __converters.toInstant(_tmp_5);
            if (_tmp_6 == null) {
              throw new IllegalStateException("Expected NON-NULL 'kotlinx.datetime.Instant', but it was NULL.");
            } else {
              _tmpUpdatedAt = _tmp_6;
            }
            _item = new ActionEntity(_tmpId,_tmpUserId,_tmpFolderId,_tmpTitle,_tmpDueDate,_tmpDueTime,_tmpIsRecurring,_tmpRecurrenceType,_tmpNotifyBefore,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Flow<List<ActionEntity>> getActionsByFolder(final String folderId) {
    final String _sql = "SELECT * FROM actions WHERE folder_id = ? ORDER BY due_date ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, folderId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"actions"}, new Callable<List<ActionEntity>>() {
      @Override
      @NonNull
      public List<ActionEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "user_id");
          final int _cursorIndexOfFolderId = CursorUtil.getColumnIndexOrThrow(_cursor, "folder_id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDueDate = CursorUtil.getColumnIndexOrThrow(_cursor, "due_date");
          final int _cursorIndexOfDueTime = CursorUtil.getColumnIndexOrThrow(_cursor, "due_time");
          final int _cursorIndexOfIsRecurring = CursorUtil.getColumnIndexOrThrow(_cursor, "is_recurring");
          final int _cursorIndexOfRecurrenceType = CursorUtil.getColumnIndexOrThrow(_cursor, "recurrence_type");
          final int _cursorIndexOfNotifyBefore = CursorUtil.getColumnIndexOrThrow(_cursor, "notify_before");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
          final List<ActionEntity> _result = new ArrayList<ActionEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ActionEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpUserId;
            _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            final String _tmpFolderId;
            _tmpFolderId = _cursor.getString(_cursorIndexOfFolderId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final Instant _tmpDueDate;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfDueDate)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfDueDate);
            }
            _tmpDueDate = __converters.toInstant(_tmp);
            final LocalTime _tmpDueTime;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfDueTime)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfDueTime);
            }
            _tmpDueTime = __converters.toLocalTime(_tmp_1);
            final boolean _tmpIsRecurring;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsRecurring);
            _tmpIsRecurring = _tmp_2 != 0;
            final String _tmpRecurrenceType;
            if (_cursor.isNull(_cursorIndexOfRecurrenceType)) {
              _tmpRecurrenceType = null;
            } else {
              _tmpRecurrenceType = _cursor.getString(_cursorIndexOfRecurrenceType);
            }
            final int _tmpNotifyBefore;
            _tmpNotifyBefore = _cursor.getInt(_cursorIndexOfNotifyBefore);
            final Instant _tmpCreatedAt;
            final Long _tmp_3;
            if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
              _tmp_3 = null;
            } else {
              _tmp_3 = _cursor.getLong(_cursorIndexOfCreatedAt);
            }
            final Instant _tmp_4 = __converters.toInstant(_tmp_3);
            if (_tmp_4 == null) {
              throw new IllegalStateException("Expected NON-NULL 'kotlinx.datetime.Instant', but it was NULL.");
            } else {
              _tmpCreatedAt = _tmp_4;
            }
            final Instant _tmpUpdatedAt;
            final Long _tmp_5;
            if (_cursor.isNull(_cursorIndexOfUpdatedAt)) {
              _tmp_5 = null;
            } else {
              _tmp_5 = _cursor.getLong(_cursorIndexOfUpdatedAt);
            }
            final Instant _tmp_6 = __converters.toInstant(_tmp_5);
            if (_tmp_6 == null) {
              throw new IllegalStateException("Expected NON-NULL 'kotlinx.datetime.Instant', but it was NULL.");
            } else {
              _tmpUpdatedAt = _tmp_6;
            }
            _item = new ActionEntity(_tmpId,_tmpUserId,_tmpFolderId,_tmpTitle,_tmpDueDate,_tmpDueTime,_tmpIsRecurring,_tmpRecurrenceType,_tmpNotifyBefore,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Flow<ActionEntity> getAction(final String id) {
    final String _sql = "SELECT * FROM actions WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, id);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"actions"}, new Callable<ActionEntity>() {
      @Override
      @Nullable
      public ActionEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "user_id");
          final int _cursorIndexOfFolderId = CursorUtil.getColumnIndexOrThrow(_cursor, "folder_id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDueDate = CursorUtil.getColumnIndexOrThrow(_cursor, "due_date");
          final int _cursorIndexOfDueTime = CursorUtil.getColumnIndexOrThrow(_cursor, "due_time");
          final int _cursorIndexOfIsRecurring = CursorUtil.getColumnIndexOrThrow(_cursor, "is_recurring");
          final int _cursorIndexOfRecurrenceType = CursorUtil.getColumnIndexOrThrow(_cursor, "recurrence_type");
          final int _cursorIndexOfNotifyBefore = CursorUtil.getColumnIndexOrThrow(_cursor, "notify_before");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
          final ActionEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpUserId;
            _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            final String _tmpFolderId;
            _tmpFolderId = _cursor.getString(_cursorIndexOfFolderId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final Instant _tmpDueDate;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfDueDate)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfDueDate);
            }
            _tmpDueDate = __converters.toInstant(_tmp);
            final LocalTime _tmpDueTime;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfDueTime)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfDueTime);
            }
            _tmpDueTime = __converters.toLocalTime(_tmp_1);
            final boolean _tmpIsRecurring;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsRecurring);
            _tmpIsRecurring = _tmp_2 != 0;
            final String _tmpRecurrenceType;
            if (_cursor.isNull(_cursorIndexOfRecurrenceType)) {
              _tmpRecurrenceType = null;
            } else {
              _tmpRecurrenceType = _cursor.getString(_cursorIndexOfRecurrenceType);
            }
            final int _tmpNotifyBefore;
            _tmpNotifyBefore = _cursor.getInt(_cursorIndexOfNotifyBefore);
            final Instant _tmpCreatedAt;
            final Long _tmp_3;
            if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
              _tmp_3 = null;
            } else {
              _tmp_3 = _cursor.getLong(_cursorIndexOfCreatedAt);
            }
            final Instant _tmp_4 = __converters.toInstant(_tmp_3);
            if (_tmp_4 == null) {
              throw new IllegalStateException("Expected NON-NULL 'kotlinx.datetime.Instant', but it was NULL.");
            } else {
              _tmpCreatedAt = _tmp_4;
            }
            final Instant _tmpUpdatedAt;
            final Long _tmp_5;
            if (_cursor.isNull(_cursorIndexOfUpdatedAt)) {
              _tmp_5 = null;
            } else {
              _tmp_5 = _cursor.getLong(_cursorIndexOfUpdatedAt);
            }
            final Instant _tmp_6 = __converters.toInstant(_tmp_5);
            if (_tmp_6 == null) {
              throw new IllegalStateException("Expected NON-NULL 'kotlinx.datetime.Instant', but it was NULL.");
            } else {
              _tmpUpdatedAt = _tmp_6;
            }
            _result = new ActionEntity(_tmpId,_tmpUserId,_tmpFolderId,_tmpTitle,_tmpDueDate,_tmpDueTime,_tmpIsRecurring,_tmpRecurrenceType,_tmpNotifyBefore,_tmpCreatedAt,_tmpUpdatedAt);
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
