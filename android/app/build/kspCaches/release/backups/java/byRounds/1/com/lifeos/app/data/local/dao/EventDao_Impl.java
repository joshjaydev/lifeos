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
import com.lifeos.app.data.local.entity.EventEntity;
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

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class EventDao_Impl implements EventDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<EventEntity> __insertionAdapterOfEventEntity;

  private final Converters __converters = new Converters();

  private final EntityDeletionOrUpdateAdapter<EventEntity> __updateAdapterOfEventEntity;

  private final SharedSQLiteStatement __preparedStmtOfDelete;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public EventDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfEventEntity = new EntityInsertionAdapter<EventEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `events` (`id`,`user_id`,`title`,`description`,`start_datetime`,`end_datetime`,`is_recurring`,`recurrence_type`,`notify_before`,`created_at`,`updated_at`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final EventEntity entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getUserId());
        statement.bindString(3, entity.getTitle());
        if (entity.getDescription() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getDescription());
        }
        final Long _tmp = __converters.fromInstant(entity.getStartDatetime());
        if (_tmp == null) {
          statement.bindNull(5);
        } else {
          statement.bindLong(5, _tmp);
        }
        final Long _tmp_1 = __converters.fromInstant(entity.getEndDatetime());
        if (_tmp_1 == null) {
          statement.bindNull(6);
        } else {
          statement.bindLong(6, _tmp_1);
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
    this.__updateAdapterOfEventEntity = new EntityDeletionOrUpdateAdapter<EventEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `events` SET `id` = ?,`user_id` = ?,`title` = ?,`description` = ?,`start_datetime` = ?,`end_datetime` = ?,`is_recurring` = ?,`recurrence_type` = ?,`notify_before` = ?,`created_at` = ?,`updated_at` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final EventEntity entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getUserId());
        statement.bindString(3, entity.getTitle());
        if (entity.getDescription() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getDescription());
        }
        final Long _tmp = __converters.fromInstant(entity.getStartDatetime());
        if (_tmp == null) {
          statement.bindNull(5);
        } else {
          statement.bindLong(5, _tmp);
        }
        final Long _tmp_1 = __converters.fromInstant(entity.getEndDatetime());
        if (_tmp_1 == null) {
          statement.bindNull(6);
        } else {
          statement.bindLong(6, _tmp_1);
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
    this.__preparedStmtOfDelete = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM events WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM events WHERE user_id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final EventEntity event, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfEventEntity.insert(event);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertAll(final List<EventEntity> events,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfEventEntity.insert(events);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final EventEntity event, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfEventEntity.handle(event);
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
  public Flow<List<EventEntity>> getEvents(final String userId) {
    final String _sql = "SELECT * FROM events WHERE user_id = ? ORDER BY start_datetime ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, userId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"events"}, new Callable<List<EventEntity>>() {
      @Override
      @NonNull
      public List<EventEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "user_id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfStartDatetime = CursorUtil.getColumnIndexOrThrow(_cursor, "start_datetime");
          final int _cursorIndexOfEndDatetime = CursorUtil.getColumnIndexOrThrow(_cursor, "end_datetime");
          final int _cursorIndexOfIsRecurring = CursorUtil.getColumnIndexOrThrow(_cursor, "is_recurring");
          final int _cursorIndexOfRecurrenceType = CursorUtil.getColumnIndexOrThrow(_cursor, "recurrence_type");
          final int _cursorIndexOfNotifyBefore = CursorUtil.getColumnIndexOrThrow(_cursor, "notify_before");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
          final List<EventEntity> _result = new ArrayList<EventEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final EventEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpUserId;
            _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final Instant _tmpStartDatetime;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfStartDatetime)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfStartDatetime);
            }
            final Instant _tmp_1 = __converters.toInstant(_tmp);
            if (_tmp_1 == null) {
              throw new IllegalStateException("Expected NON-NULL 'kotlinx.datetime.Instant', but it was NULL.");
            } else {
              _tmpStartDatetime = _tmp_1;
            }
            final Instant _tmpEndDatetime;
            final Long _tmp_2;
            if (_cursor.isNull(_cursorIndexOfEndDatetime)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getLong(_cursorIndexOfEndDatetime);
            }
            final Instant _tmp_3 = __converters.toInstant(_tmp_2);
            if (_tmp_3 == null) {
              throw new IllegalStateException("Expected NON-NULL 'kotlinx.datetime.Instant', but it was NULL.");
            } else {
              _tmpEndDatetime = _tmp_3;
            }
            final boolean _tmpIsRecurring;
            final int _tmp_4;
            _tmp_4 = _cursor.getInt(_cursorIndexOfIsRecurring);
            _tmpIsRecurring = _tmp_4 != 0;
            final String _tmpRecurrenceType;
            if (_cursor.isNull(_cursorIndexOfRecurrenceType)) {
              _tmpRecurrenceType = null;
            } else {
              _tmpRecurrenceType = _cursor.getString(_cursorIndexOfRecurrenceType);
            }
            final int _tmpNotifyBefore;
            _tmpNotifyBefore = _cursor.getInt(_cursorIndexOfNotifyBefore);
            final Instant _tmpCreatedAt;
            final Long _tmp_5;
            if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
              _tmp_5 = null;
            } else {
              _tmp_5 = _cursor.getLong(_cursorIndexOfCreatedAt);
            }
            final Instant _tmp_6 = __converters.toInstant(_tmp_5);
            if (_tmp_6 == null) {
              throw new IllegalStateException("Expected NON-NULL 'kotlinx.datetime.Instant', but it was NULL.");
            } else {
              _tmpCreatedAt = _tmp_6;
            }
            final Instant _tmpUpdatedAt;
            final Long _tmp_7;
            if (_cursor.isNull(_cursorIndexOfUpdatedAt)) {
              _tmp_7 = null;
            } else {
              _tmp_7 = _cursor.getLong(_cursorIndexOfUpdatedAt);
            }
            final Instant _tmp_8 = __converters.toInstant(_tmp_7);
            if (_tmp_8 == null) {
              throw new IllegalStateException("Expected NON-NULL 'kotlinx.datetime.Instant', but it was NULL.");
            } else {
              _tmpUpdatedAt = _tmp_8;
            }
            _item = new EventEntity(_tmpId,_tmpUserId,_tmpTitle,_tmpDescription,_tmpStartDatetime,_tmpEndDatetime,_tmpIsRecurring,_tmpRecurrenceType,_tmpNotifyBefore,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Flow<List<EventEntity>> getEventsBetween(final String userId, final long startTime,
      final long endTime) {
    final String _sql = "SELECT * FROM events WHERE user_id = ? AND start_datetime >= ? AND start_datetime <= ? ORDER BY start_datetime ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 3);
    int _argIndex = 1;
    _statement.bindString(_argIndex, userId);
    _argIndex = 2;
    _statement.bindLong(_argIndex, startTime);
    _argIndex = 3;
    _statement.bindLong(_argIndex, endTime);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"events"}, new Callable<List<EventEntity>>() {
      @Override
      @NonNull
      public List<EventEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "user_id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfStartDatetime = CursorUtil.getColumnIndexOrThrow(_cursor, "start_datetime");
          final int _cursorIndexOfEndDatetime = CursorUtil.getColumnIndexOrThrow(_cursor, "end_datetime");
          final int _cursorIndexOfIsRecurring = CursorUtil.getColumnIndexOrThrow(_cursor, "is_recurring");
          final int _cursorIndexOfRecurrenceType = CursorUtil.getColumnIndexOrThrow(_cursor, "recurrence_type");
          final int _cursorIndexOfNotifyBefore = CursorUtil.getColumnIndexOrThrow(_cursor, "notify_before");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
          final List<EventEntity> _result = new ArrayList<EventEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final EventEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpUserId;
            _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final Instant _tmpStartDatetime;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfStartDatetime)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfStartDatetime);
            }
            final Instant _tmp_1 = __converters.toInstant(_tmp);
            if (_tmp_1 == null) {
              throw new IllegalStateException("Expected NON-NULL 'kotlinx.datetime.Instant', but it was NULL.");
            } else {
              _tmpStartDatetime = _tmp_1;
            }
            final Instant _tmpEndDatetime;
            final Long _tmp_2;
            if (_cursor.isNull(_cursorIndexOfEndDatetime)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getLong(_cursorIndexOfEndDatetime);
            }
            final Instant _tmp_3 = __converters.toInstant(_tmp_2);
            if (_tmp_3 == null) {
              throw new IllegalStateException("Expected NON-NULL 'kotlinx.datetime.Instant', but it was NULL.");
            } else {
              _tmpEndDatetime = _tmp_3;
            }
            final boolean _tmpIsRecurring;
            final int _tmp_4;
            _tmp_4 = _cursor.getInt(_cursorIndexOfIsRecurring);
            _tmpIsRecurring = _tmp_4 != 0;
            final String _tmpRecurrenceType;
            if (_cursor.isNull(_cursorIndexOfRecurrenceType)) {
              _tmpRecurrenceType = null;
            } else {
              _tmpRecurrenceType = _cursor.getString(_cursorIndexOfRecurrenceType);
            }
            final int _tmpNotifyBefore;
            _tmpNotifyBefore = _cursor.getInt(_cursorIndexOfNotifyBefore);
            final Instant _tmpCreatedAt;
            final Long _tmp_5;
            if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
              _tmp_5 = null;
            } else {
              _tmp_5 = _cursor.getLong(_cursorIndexOfCreatedAt);
            }
            final Instant _tmp_6 = __converters.toInstant(_tmp_5);
            if (_tmp_6 == null) {
              throw new IllegalStateException("Expected NON-NULL 'kotlinx.datetime.Instant', but it was NULL.");
            } else {
              _tmpCreatedAt = _tmp_6;
            }
            final Instant _tmpUpdatedAt;
            final Long _tmp_7;
            if (_cursor.isNull(_cursorIndexOfUpdatedAt)) {
              _tmp_7 = null;
            } else {
              _tmp_7 = _cursor.getLong(_cursorIndexOfUpdatedAt);
            }
            final Instant _tmp_8 = __converters.toInstant(_tmp_7);
            if (_tmp_8 == null) {
              throw new IllegalStateException("Expected NON-NULL 'kotlinx.datetime.Instant', but it was NULL.");
            } else {
              _tmpUpdatedAt = _tmp_8;
            }
            _item = new EventEntity(_tmpId,_tmpUserId,_tmpTitle,_tmpDescription,_tmpStartDatetime,_tmpEndDatetime,_tmpIsRecurring,_tmpRecurrenceType,_tmpNotifyBefore,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Flow<EventEntity> getEvent(final String id) {
    final String _sql = "SELECT * FROM events WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, id);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"events"}, new Callable<EventEntity>() {
      @Override
      @Nullable
      public EventEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "user_id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfStartDatetime = CursorUtil.getColumnIndexOrThrow(_cursor, "start_datetime");
          final int _cursorIndexOfEndDatetime = CursorUtil.getColumnIndexOrThrow(_cursor, "end_datetime");
          final int _cursorIndexOfIsRecurring = CursorUtil.getColumnIndexOrThrow(_cursor, "is_recurring");
          final int _cursorIndexOfRecurrenceType = CursorUtil.getColumnIndexOrThrow(_cursor, "recurrence_type");
          final int _cursorIndexOfNotifyBefore = CursorUtil.getColumnIndexOrThrow(_cursor, "notify_before");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
          final EventEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpUserId;
            _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final Instant _tmpStartDatetime;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfStartDatetime)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfStartDatetime);
            }
            final Instant _tmp_1 = __converters.toInstant(_tmp);
            if (_tmp_1 == null) {
              throw new IllegalStateException("Expected NON-NULL 'kotlinx.datetime.Instant', but it was NULL.");
            } else {
              _tmpStartDatetime = _tmp_1;
            }
            final Instant _tmpEndDatetime;
            final Long _tmp_2;
            if (_cursor.isNull(_cursorIndexOfEndDatetime)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getLong(_cursorIndexOfEndDatetime);
            }
            final Instant _tmp_3 = __converters.toInstant(_tmp_2);
            if (_tmp_3 == null) {
              throw new IllegalStateException("Expected NON-NULL 'kotlinx.datetime.Instant', but it was NULL.");
            } else {
              _tmpEndDatetime = _tmp_3;
            }
            final boolean _tmpIsRecurring;
            final int _tmp_4;
            _tmp_4 = _cursor.getInt(_cursorIndexOfIsRecurring);
            _tmpIsRecurring = _tmp_4 != 0;
            final String _tmpRecurrenceType;
            if (_cursor.isNull(_cursorIndexOfRecurrenceType)) {
              _tmpRecurrenceType = null;
            } else {
              _tmpRecurrenceType = _cursor.getString(_cursorIndexOfRecurrenceType);
            }
            final int _tmpNotifyBefore;
            _tmpNotifyBefore = _cursor.getInt(_cursorIndexOfNotifyBefore);
            final Instant _tmpCreatedAt;
            final Long _tmp_5;
            if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
              _tmp_5 = null;
            } else {
              _tmp_5 = _cursor.getLong(_cursorIndexOfCreatedAt);
            }
            final Instant _tmp_6 = __converters.toInstant(_tmp_5);
            if (_tmp_6 == null) {
              throw new IllegalStateException("Expected NON-NULL 'kotlinx.datetime.Instant', but it was NULL.");
            } else {
              _tmpCreatedAt = _tmp_6;
            }
            final Instant _tmpUpdatedAt;
            final Long _tmp_7;
            if (_cursor.isNull(_cursorIndexOfUpdatedAt)) {
              _tmp_7 = null;
            } else {
              _tmp_7 = _cursor.getLong(_cursorIndexOfUpdatedAt);
            }
            final Instant _tmp_8 = __converters.toInstant(_tmp_7);
            if (_tmp_8 == null) {
              throw new IllegalStateException("Expected NON-NULL 'kotlinx.datetime.Instant', but it was NULL.");
            } else {
              _tmpUpdatedAt = _tmp_8;
            }
            _result = new EventEntity(_tmpId,_tmpUserId,_tmpTitle,_tmpDescription,_tmpStartDatetime,_tmpEndDatetime,_tmpIsRecurring,_tmpRecurrenceType,_tmpNotifyBefore,_tmpCreatedAt,_tmpUpdatedAt);
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
