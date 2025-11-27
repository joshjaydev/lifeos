package com.lifeos.app.data.local.dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.lifeos.app.data.local.Converters;
import com.lifeos.app.data.local.entity.ActionCompletionEntity;
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
public final class ActionCompletionDao_Impl implements ActionCompletionDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ActionCompletionEntity> __insertionAdapterOfActionCompletionEntity;

  private final Converters __converters = new Converters();

  private final SharedSQLiteStatement __preparedStmtOfDelete;

  private final SharedSQLiteStatement __preparedStmtOfDeleteByAction;

  public ActionCompletionDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfActionCompletionEntity = new EntityInsertionAdapter<ActionCompletionEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `action_completions` (`id`,`action_id`,`completed_date`,`completed_at`) VALUES (?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ActionCompletionEntity entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getActionId());
        final String _tmp = __converters.fromLocalDate(entity.getCompletedDate());
        if (_tmp == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, _tmp);
        }
        final Long _tmp_1 = __converters.fromInstant(entity.getCompletedAt());
        if (_tmp_1 == null) {
          statement.bindNull(4);
        } else {
          statement.bindLong(4, _tmp_1);
        }
      }
    };
    this.__preparedStmtOfDelete = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM action_completions WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteByAction = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM action_completions WHERE action_id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final ActionCompletionEntity completion,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfActionCompletionEntity.insert(completion);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertAll(final List<ActionCompletionEntity> completions,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfActionCompletionEntity.insert(completions);
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
  public Object deleteByAction(final String actionId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteByAction.acquire();
        int _argIndex = 1;
        _stmt.bindString(_argIndex, actionId);
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
          __preparedStmtOfDeleteByAction.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<ActionCompletionEntity>> getCompletions(final String actionId) {
    final String _sql = "SELECT * FROM action_completions WHERE action_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, actionId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"action_completions"}, new Callable<List<ActionCompletionEntity>>() {
      @Override
      @NonNull
      public List<ActionCompletionEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfActionId = CursorUtil.getColumnIndexOrThrow(_cursor, "action_id");
          final int _cursorIndexOfCompletedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "completed_date");
          final int _cursorIndexOfCompletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "completed_at");
          final List<ActionCompletionEntity> _result = new ArrayList<ActionCompletionEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ActionCompletionEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpActionId;
            _tmpActionId = _cursor.getString(_cursorIndexOfActionId);
            final LocalDate _tmpCompletedDate;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfCompletedDate)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfCompletedDate);
            }
            final LocalDate _tmp_1 = __converters.toLocalDate(_tmp);
            if (_tmp_1 == null) {
              throw new IllegalStateException("Expected NON-NULL 'kotlinx.datetime.LocalDate', but it was NULL.");
            } else {
              _tmpCompletedDate = _tmp_1;
            }
            final Instant _tmpCompletedAt;
            final Long _tmp_2;
            if (_cursor.isNull(_cursorIndexOfCompletedAt)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getLong(_cursorIndexOfCompletedAt);
            }
            final Instant _tmp_3 = __converters.toInstant(_tmp_2);
            if (_tmp_3 == null) {
              throw new IllegalStateException("Expected NON-NULL 'kotlinx.datetime.Instant', but it was NULL.");
            } else {
              _tmpCompletedAt = _tmp_3;
            }
            _item = new ActionCompletionEntity(_tmpId,_tmpActionId,_tmpCompletedDate,_tmpCompletedAt);
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
