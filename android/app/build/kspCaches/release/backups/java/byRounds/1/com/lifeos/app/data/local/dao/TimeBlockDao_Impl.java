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
import com.lifeos.app.data.local.entity.TimeBlockEntity;
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
import kotlinx.datetime.LocalTime;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class TimeBlockDao_Impl implements TimeBlockDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<TimeBlockEntity> __insertionAdapterOfTimeBlockEntity;

  private final Converters __converters = new Converters();

  private final EntityDeletionOrUpdateAdapter<TimeBlockEntity> __updateAdapterOfTimeBlockEntity;

  private final SharedSQLiteStatement __preparedStmtOfDelete;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public TimeBlockDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTimeBlockEntity = new EntityInsertionAdapter<TimeBlockEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `time_blocks` (`id`,`user_id`,`action_id`,`block_date`,`start_time`,`end_time`,`created_at`) VALUES (?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final TimeBlockEntity entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getUserId());
        statement.bindString(3, entity.getActionId());
        final String _tmp = __converters.fromLocalDate(entity.getBlockDate());
        if (_tmp == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, _tmp);
        }
        final String _tmp_1 = __converters.fromLocalTime(entity.getStartTime());
        if (_tmp_1 == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, _tmp_1);
        }
        final String _tmp_2 = __converters.fromLocalTime(entity.getEndTime());
        if (_tmp_2 == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, _tmp_2);
        }
        final Long _tmp_3 = __converters.fromInstant(entity.getCreatedAt());
        if (_tmp_3 == null) {
          statement.bindNull(7);
        } else {
          statement.bindLong(7, _tmp_3);
        }
      }
    };
    this.__updateAdapterOfTimeBlockEntity = new EntityDeletionOrUpdateAdapter<TimeBlockEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `time_blocks` SET `id` = ?,`user_id` = ?,`action_id` = ?,`block_date` = ?,`start_time` = ?,`end_time` = ?,`created_at` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final TimeBlockEntity entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getUserId());
        statement.bindString(3, entity.getActionId());
        final String _tmp = __converters.fromLocalDate(entity.getBlockDate());
        if (_tmp == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, _tmp);
        }
        final String _tmp_1 = __converters.fromLocalTime(entity.getStartTime());
        if (_tmp_1 == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, _tmp_1);
        }
        final String _tmp_2 = __converters.fromLocalTime(entity.getEndTime());
        if (_tmp_2 == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, _tmp_2);
        }
        final Long _tmp_3 = __converters.fromInstant(entity.getCreatedAt());
        if (_tmp_3 == null) {
          statement.bindNull(7);
        } else {
          statement.bindLong(7, _tmp_3);
        }
        statement.bindString(8, entity.getId());
      }
    };
    this.__preparedStmtOfDelete = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM time_blocks WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM time_blocks WHERE user_id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final TimeBlockEntity timeBlock,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfTimeBlockEntity.insert(timeBlock);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertAll(final List<TimeBlockEntity> timeBlocks,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfTimeBlockEntity.insert(timeBlocks);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final TimeBlockEntity timeBlock,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfTimeBlockEntity.handle(timeBlock);
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
  public Flow<List<TimeBlockEntity>> getTimeBlocks(final String userId, final String date) {
    final String _sql = "SELECT * FROM time_blocks WHERE user_id = ? AND block_date = ? ORDER BY start_time ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindString(_argIndex, userId);
    _argIndex = 2;
    _statement.bindString(_argIndex, date);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"time_blocks"}, new Callable<List<TimeBlockEntity>>() {
      @Override
      @NonNull
      public List<TimeBlockEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "user_id");
          final int _cursorIndexOfActionId = CursorUtil.getColumnIndexOrThrow(_cursor, "action_id");
          final int _cursorIndexOfBlockDate = CursorUtil.getColumnIndexOrThrow(_cursor, "block_date");
          final int _cursorIndexOfStartTime = CursorUtil.getColumnIndexOrThrow(_cursor, "start_time");
          final int _cursorIndexOfEndTime = CursorUtil.getColumnIndexOrThrow(_cursor, "end_time");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
          final List<TimeBlockEntity> _result = new ArrayList<TimeBlockEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final TimeBlockEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpUserId;
            _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            final String _tmpActionId;
            _tmpActionId = _cursor.getString(_cursorIndexOfActionId);
            final LocalDate _tmpBlockDate;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfBlockDate)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfBlockDate);
            }
            final LocalDate _tmp_1 = __converters.toLocalDate(_tmp);
            if (_tmp_1 == null) {
              throw new IllegalStateException("Expected NON-NULL 'kotlinx.datetime.LocalDate', but it was NULL.");
            } else {
              _tmpBlockDate = _tmp_1;
            }
            final LocalTime _tmpStartTime;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfStartTime)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfStartTime);
            }
            final LocalTime _tmp_3 = __converters.toLocalTime(_tmp_2);
            if (_tmp_3 == null) {
              throw new IllegalStateException("Expected NON-NULL 'kotlinx.datetime.LocalTime', but it was NULL.");
            } else {
              _tmpStartTime = _tmp_3;
            }
            final LocalTime _tmpEndTime;
            final String _tmp_4;
            if (_cursor.isNull(_cursorIndexOfEndTime)) {
              _tmp_4 = null;
            } else {
              _tmp_4 = _cursor.getString(_cursorIndexOfEndTime);
            }
            final LocalTime _tmp_5 = __converters.toLocalTime(_tmp_4);
            if (_tmp_5 == null) {
              throw new IllegalStateException("Expected NON-NULL 'kotlinx.datetime.LocalTime', but it was NULL.");
            } else {
              _tmpEndTime = _tmp_5;
            }
            final Instant _tmpCreatedAt;
            final Long _tmp_6;
            if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
              _tmp_6 = null;
            } else {
              _tmp_6 = _cursor.getLong(_cursorIndexOfCreatedAt);
            }
            final Instant _tmp_7 = __converters.toInstant(_tmp_6);
            if (_tmp_7 == null) {
              throw new IllegalStateException("Expected NON-NULL 'kotlinx.datetime.Instant', but it was NULL.");
            } else {
              _tmpCreatedAt = _tmp_7;
            }
            _item = new TimeBlockEntity(_tmpId,_tmpUserId,_tmpActionId,_tmpBlockDate,_tmpStartTime,_tmpEndTime,_tmpCreatedAt);
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
  public Flow<TimeBlockEntity> getTimeBlock(final String id) {
    final String _sql = "SELECT * FROM time_blocks WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, id);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"time_blocks"}, new Callable<TimeBlockEntity>() {
      @Override
      @Nullable
      public TimeBlockEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "user_id");
          final int _cursorIndexOfActionId = CursorUtil.getColumnIndexOrThrow(_cursor, "action_id");
          final int _cursorIndexOfBlockDate = CursorUtil.getColumnIndexOrThrow(_cursor, "block_date");
          final int _cursorIndexOfStartTime = CursorUtil.getColumnIndexOrThrow(_cursor, "start_time");
          final int _cursorIndexOfEndTime = CursorUtil.getColumnIndexOrThrow(_cursor, "end_time");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
          final TimeBlockEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpUserId;
            _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            final String _tmpActionId;
            _tmpActionId = _cursor.getString(_cursorIndexOfActionId);
            final LocalDate _tmpBlockDate;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfBlockDate)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfBlockDate);
            }
            final LocalDate _tmp_1 = __converters.toLocalDate(_tmp);
            if (_tmp_1 == null) {
              throw new IllegalStateException("Expected NON-NULL 'kotlinx.datetime.LocalDate', but it was NULL.");
            } else {
              _tmpBlockDate = _tmp_1;
            }
            final LocalTime _tmpStartTime;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfStartTime)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfStartTime);
            }
            final LocalTime _tmp_3 = __converters.toLocalTime(_tmp_2);
            if (_tmp_3 == null) {
              throw new IllegalStateException("Expected NON-NULL 'kotlinx.datetime.LocalTime', but it was NULL.");
            } else {
              _tmpStartTime = _tmp_3;
            }
            final LocalTime _tmpEndTime;
            final String _tmp_4;
            if (_cursor.isNull(_cursorIndexOfEndTime)) {
              _tmp_4 = null;
            } else {
              _tmp_4 = _cursor.getString(_cursorIndexOfEndTime);
            }
            final LocalTime _tmp_5 = __converters.toLocalTime(_tmp_4);
            if (_tmp_5 == null) {
              throw new IllegalStateException("Expected NON-NULL 'kotlinx.datetime.LocalTime', but it was NULL.");
            } else {
              _tmpEndTime = _tmp_5;
            }
            final Instant _tmpCreatedAt;
            final Long _tmp_6;
            if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
              _tmp_6 = null;
            } else {
              _tmp_6 = _cursor.getLong(_cursorIndexOfCreatedAt);
            }
            final Instant _tmp_7 = __converters.toInstant(_tmp_6);
            if (_tmp_7 == null) {
              throw new IllegalStateException("Expected NON-NULL 'kotlinx.datetime.Instant', but it was NULL.");
            } else {
              _tmpCreatedAt = _tmp_7;
            }
            _result = new TimeBlockEntity(_tmpId,_tmpUserId,_tmpActionId,_tmpBlockDate,_tmpStartTime,_tmpEndTime,_tmpCreatedAt);
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
