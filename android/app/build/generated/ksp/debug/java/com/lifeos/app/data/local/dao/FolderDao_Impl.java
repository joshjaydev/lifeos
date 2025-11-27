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
import com.lifeos.app.data.local.entity.FolderEntity;
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
public final class FolderDao_Impl implements FolderDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<FolderEntity> __insertionAdapterOfFolderEntity;

  private final Converters __converters = new Converters();

  private final EntityDeletionOrUpdateAdapter<FolderEntity> __updateAdapterOfFolderEntity;

  private final SharedSQLiteStatement __preparedStmtOfDelete;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public FolderDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfFolderEntity = new EntityInsertionAdapter<FolderEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `folders` (`id`,`user_id`,`name`,`color`,`is_default`,`goal_id`,`created_at`,`updated_at`) VALUES (?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final FolderEntity entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getUserId());
        statement.bindString(3, entity.getName());
        if (entity.getColor() == null) {
          statement.bindNull(4);
        } else {
          statement.bindLong(4, entity.getColor());
        }
        final int _tmp = entity.isDefault() ? 1 : 0;
        statement.bindLong(5, _tmp);
        if (entity.getGoalId() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getGoalId());
        }
        final Long _tmp_1 = __converters.fromInstant(entity.getCreatedAt());
        if (_tmp_1 == null) {
          statement.bindNull(7);
        } else {
          statement.bindLong(7, _tmp_1);
        }
        final Long _tmp_2 = __converters.fromInstant(entity.getUpdatedAt());
        if (_tmp_2 == null) {
          statement.bindNull(8);
        } else {
          statement.bindLong(8, _tmp_2);
        }
      }
    };
    this.__updateAdapterOfFolderEntity = new EntityDeletionOrUpdateAdapter<FolderEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `folders` SET `id` = ?,`user_id` = ?,`name` = ?,`color` = ?,`is_default` = ?,`goal_id` = ?,`created_at` = ?,`updated_at` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final FolderEntity entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getUserId());
        statement.bindString(3, entity.getName());
        if (entity.getColor() == null) {
          statement.bindNull(4);
        } else {
          statement.bindLong(4, entity.getColor());
        }
        final int _tmp = entity.isDefault() ? 1 : 0;
        statement.bindLong(5, _tmp);
        if (entity.getGoalId() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getGoalId());
        }
        final Long _tmp_1 = __converters.fromInstant(entity.getCreatedAt());
        if (_tmp_1 == null) {
          statement.bindNull(7);
        } else {
          statement.bindLong(7, _tmp_1);
        }
        final Long _tmp_2 = __converters.fromInstant(entity.getUpdatedAt());
        if (_tmp_2 == null) {
          statement.bindNull(8);
        } else {
          statement.bindLong(8, _tmp_2);
        }
        statement.bindString(9, entity.getId());
      }
    };
    this.__preparedStmtOfDelete = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM folders WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM folders WHERE user_id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final FolderEntity folder, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfFolderEntity.insert(folder);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertAll(final List<FolderEntity> folders,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfFolderEntity.insert(folders);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final FolderEntity folder, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfFolderEntity.handle(folder);
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
  public Flow<List<FolderEntity>> getFolders(final String userId) {
    final String _sql = "SELECT * FROM folders WHERE user_id = ? ORDER BY is_default DESC, name ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, userId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"folders"}, new Callable<List<FolderEntity>>() {
      @Override
      @NonNull
      public List<FolderEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "user_id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfColor = CursorUtil.getColumnIndexOrThrow(_cursor, "color");
          final int _cursorIndexOfIsDefault = CursorUtil.getColumnIndexOrThrow(_cursor, "is_default");
          final int _cursorIndexOfGoalId = CursorUtil.getColumnIndexOrThrow(_cursor, "goal_id");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
          final List<FolderEntity> _result = new ArrayList<FolderEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final FolderEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpUserId;
            _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final Long _tmpColor;
            if (_cursor.isNull(_cursorIndexOfColor)) {
              _tmpColor = null;
            } else {
              _tmpColor = _cursor.getLong(_cursorIndexOfColor);
            }
            final boolean _tmpIsDefault;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsDefault);
            _tmpIsDefault = _tmp != 0;
            final String _tmpGoalId;
            if (_cursor.isNull(_cursorIndexOfGoalId)) {
              _tmpGoalId = null;
            } else {
              _tmpGoalId = _cursor.getString(_cursorIndexOfGoalId);
            }
            final Instant _tmpCreatedAt;
            final Long _tmp_1;
            if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getLong(_cursorIndexOfCreatedAt);
            }
            final Instant _tmp_2 = __converters.toInstant(_tmp_1);
            if (_tmp_2 == null) {
              throw new IllegalStateException("Expected NON-NULL 'kotlinx.datetime.Instant', but it was NULL.");
            } else {
              _tmpCreatedAt = _tmp_2;
            }
            final Instant _tmpUpdatedAt;
            final Long _tmp_3;
            if (_cursor.isNull(_cursorIndexOfUpdatedAt)) {
              _tmp_3 = null;
            } else {
              _tmp_3 = _cursor.getLong(_cursorIndexOfUpdatedAt);
            }
            final Instant _tmp_4 = __converters.toInstant(_tmp_3);
            if (_tmp_4 == null) {
              throw new IllegalStateException("Expected NON-NULL 'kotlinx.datetime.Instant', but it was NULL.");
            } else {
              _tmpUpdatedAt = _tmp_4;
            }
            _item = new FolderEntity(_tmpId,_tmpUserId,_tmpName,_tmpColor,_tmpIsDefault,_tmpGoalId,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Flow<FolderEntity> getFolder(final String id) {
    final String _sql = "SELECT * FROM folders WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, id);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"folders"}, new Callable<FolderEntity>() {
      @Override
      @Nullable
      public FolderEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "user_id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfColor = CursorUtil.getColumnIndexOrThrow(_cursor, "color");
          final int _cursorIndexOfIsDefault = CursorUtil.getColumnIndexOrThrow(_cursor, "is_default");
          final int _cursorIndexOfGoalId = CursorUtil.getColumnIndexOrThrow(_cursor, "goal_id");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
          final FolderEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpUserId;
            _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final Long _tmpColor;
            if (_cursor.isNull(_cursorIndexOfColor)) {
              _tmpColor = null;
            } else {
              _tmpColor = _cursor.getLong(_cursorIndexOfColor);
            }
            final boolean _tmpIsDefault;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsDefault);
            _tmpIsDefault = _tmp != 0;
            final String _tmpGoalId;
            if (_cursor.isNull(_cursorIndexOfGoalId)) {
              _tmpGoalId = null;
            } else {
              _tmpGoalId = _cursor.getString(_cursorIndexOfGoalId);
            }
            final Instant _tmpCreatedAt;
            final Long _tmp_1;
            if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getLong(_cursorIndexOfCreatedAt);
            }
            final Instant _tmp_2 = __converters.toInstant(_tmp_1);
            if (_tmp_2 == null) {
              throw new IllegalStateException("Expected NON-NULL 'kotlinx.datetime.Instant', but it was NULL.");
            } else {
              _tmpCreatedAt = _tmp_2;
            }
            final Instant _tmpUpdatedAt;
            final Long _tmp_3;
            if (_cursor.isNull(_cursorIndexOfUpdatedAt)) {
              _tmp_3 = null;
            } else {
              _tmp_3 = _cursor.getLong(_cursorIndexOfUpdatedAt);
            }
            final Instant _tmp_4 = __converters.toInstant(_tmp_3);
            if (_tmp_4 == null) {
              throw new IllegalStateException("Expected NON-NULL 'kotlinx.datetime.Instant', but it was NULL.");
            } else {
              _tmpUpdatedAt = _tmp_4;
            }
            _result = new FolderEntity(_tmpId,_tmpUserId,_tmpName,_tmpColor,_tmpIsDefault,_tmpGoalId,_tmpCreatedAt,_tmpUpdatedAt);
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
