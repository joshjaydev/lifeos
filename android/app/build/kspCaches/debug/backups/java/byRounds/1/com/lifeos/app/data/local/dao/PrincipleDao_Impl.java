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
import com.lifeos.app.data.local.entity.PrincipleEntity;
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
public final class PrincipleDao_Impl implements PrincipleDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<PrincipleEntity> __insertionAdapterOfPrincipleEntity;

  private final Converters __converters = new Converters();

  private final EntityDeletionOrUpdateAdapter<PrincipleEntity> __updateAdapterOfPrincipleEntity;

  private final SharedSQLiteStatement __preparedStmtOfDelete;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public PrincipleDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPrincipleEntity = new EntityInsertionAdapter<PrincipleEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `principles` (`id`,`user_id`,`parent_id`,`fundamental_truth`,`experience`,`created_at`,`updated_at`) VALUES (?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final PrincipleEntity entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getUserId());
        if (entity.getParentId() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getParentId());
        }
        statement.bindString(4, entity.getFundamentalTruth());
        if (entity.getExperience() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getExperience());
        }
        final Long _tmp = __converters.fromInstant(entity.getCreatedAt());
        if (_tmp == null) {
          statement.bindNull(6);
        } else {
          statement.bindLong(6, _tmp);
        }
        final Long _tmp_1 = __converters.fromInstant(entity.getUpdatedAt());
        if (_tmp_1 == null) {
          statement.bindNull(7);
        } else {
          statement.bindLong(7, _tmp_1);
        }
      }
    };
    this.__updateAdapterOfPrincipleEntity = new EntityDeletionOrUpdateAdapter<PrincipleEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `principles` SET `id` = ?,`user_id` = ?,`parent_id` = ?,`fundamental_truth` = ?,`experience` = ?,`created_at` = ?,`updated_at` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final PrincipleEntity entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getUserId());
        if (entity.getParentId() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getParentId());
        }
        statement.bindString(4, entity.getFundamentalTruth());
        if (entity.getExperience() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getExperience());
        }
        final Long _tmp = __converters.fromInstant(entity.getCreatedAt());
        if (_tmp == null) {
          statement.bindNull(6);
        } else {
          statement.bindLong(6, _tmp);
        }
        final Long _tmp_1 = __converters.fromInstant(entity.getUpdatedAt());
        if (_tmp_1 == null) {
          statement.bindNull(7);
        } else {
          statement.bindLong(7, _tmp_1);
        }
        statement.bindString(8, entity.getId());
      }
    };
    this.__preparedStmtOfDelete = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM principles WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM principles WHERE user_id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final PrincipleEntity principle,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfPrincipleEntity.insert(principle);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertAll(final List<PrincipleEntity> principles,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfPrincipleEntity.insert(principles);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final PrincipleEntity principle,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfPrincipleEntity.handle(principle);
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
  public Flow<List<PrincipleEntity>> getPrinciples(final String userId) {
    final String _sql = "SELECT * FROM principles WHERE user_id = ? ORDER BY created_at ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, userId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"principles"}, new Callable<List<PrincipleEntity>>() {
      @Override
      @NonNull
      public List<PrincipleEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "user_id");
          final int _cursorIndexOfParentId = CursorUtil.getColumnIndexOrThrow(_cursor, "parent_id");
          final int _cursorIndexOfFundamentalTruth = CursorUtil.getColumnIndexOrThrow(_cursor, "fundamental_truth");
          final int _cursorIndexOfExperience = CursorUtil.getColumnIndexOrThrow(_cursor, "experience");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
          final List<PrincipleEntity> _result = new ArrayList<PrincipleEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final PrincipleEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpUserId;
            _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            final String _tmpParentId;
            if (_cursor.isNull(_cursorIndexOfParentId)) {
              _tmpParentId = null;
            } else {
              _tmpParentId = _cursor.getString(_cursorIndexOfParentId);
            }
            final String _tmpFundamentalTruth;
            _tmpFundamentalTruth = _cursor.getString(_cursorIndexOfFundamentalTruth);
            final String _tmpExperience;
            if (_cursor.isNull(_cursorIndexOfExperience)) {
              _tmpExperience = null;
            } else {
              _tmpExperience = _cursor.getString(_cursorIndexOfExperience);
            }
            final Instant _tmpCreatedAt;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfCreatedAt);
            }
            final Instant _tmp_1 = __converters.toInstant(_tmp);
            if (_tmp_1 == null) {
              throw new IllegalStateException("Expected NON-NULL 'kotlinx.datetime.Instant', but it was NULL.");
            } else {
              _tmpCreatedAt = _tmp_1;
            }
            final Instant _tmpUpdatedAt;
            final Long _tmp_2;
            if (_cursor.isNull(_cursorIndexOfUpdatedAt)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getLong(_cursorIndexOfUpdatedAt);
            }
            final Instant _tmp_3 = __converters.toInstant(_tmp_2);
            if (_tmp_3 == null) {
              throw new IllegalStateException("Expected NON-NULL 'kotlinx.datetime.Instant', but it was NULL.");
            } else {
              _tmpUpdatedAt = _tmp_3;
            }
            _item = new PrincipleEntity(_tmpId,_tmpUserId,_tmpParentId,_tmpFundamentalTruth,_tmpExperience,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Flow<List<PrincipleEntity>> getSubPrinciples(final String parentId) {
    final String _sql = "SELECT * FROM principles WHERE parent_id = ? ORDER BY created_at ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, parentId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"principles"}, new Callable<List<PrincipleEntity>>() {
      @Override
      @NonNull
      public List<PrincipleEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "user_id");
          final int _cursorIndexOfParentId = CursorUtil.getColumnIndexOrThrow(_cursor, "parent_id");
          final int _cursorIndexOfFundamentalTruth = CursorUtil.getColumnIndexOrThrow(_cursor, "fundamental_truth");
          final int _cursorIndexOfExperience = CursorUtil.getColumnIndexOrThrow(_cursor, "experience");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
          final List<PrincipleEntity> _result = new ArrayList<PrincipleEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final PrincipleEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpUserId;
            _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            final String _tmpParentId;
            if (_cursor.isNull(_cursorIndexOfParentId)) {
              _tmpParentId = null;
            } else {
              _tmpParentId = _cursor.getString(_cursorIndexOfParentId);
            }
            final String _tmpFundamentalTruth;
            _tmpFundamentalTruth = _cursor.getString(_cursorIndexOfFundamentalTruth);
            final String _tmpExperience;
            if (_cursor.isNull(_cursorIndexOfExperience)) {
              _tmpExperience = null;
            } else {
              _tmpExperience = _cursor.getString(_cursorIndexOfExperience);
            }
            final Instant _tmpCreatedAt;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfCreatedAt);
            }
            final Instant _tmp_1 = __converters.toInstant(_tmp);
            if (_tmp_1 == null) {
              throw new IllegalStateException("Expected NON-NULL 'kotlinx.datetime.Instant', but it was NULL.");
            } else {
              _tmpCreatedAt = _tmp_1;
            }
            final Instant _tmpUpdatedAt;
            final Long _tmp_2;
            if (_cursor.isNull(_cursorIndexOfUpdatedAt)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getLong(_cursorIndexOfUpdatedAt);
            }
            final Instant _tmp_3 = __converters.toInstant(_tmp_2);
            if (_tmp_3 == null) {
              throw new IllegalStateException("Expected NON-NULL 'kotlinx.datetime.Instant', but it was NULL.");
            } else {
              _tmpUpdatedAt = _tmp_3;
            }
            _item = new PrincipleEntity(_tmpId,_tmpUserId,_tmpParentId,_tmpFundamentalTruth,_tmpExperience,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Flow<PrincipleEntity> getPrinciple(final String id) {
    final String _sql = "SELECT * FROM principles WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, id);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"principles"}, new Callable<PrincipleEntity>() {
      @Override
      @Nullable
      public PrincipleEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "user_id");
          final int _cursorIndexOfParentId = CursorUtil.getColumnIndexOrThrow(_cursor, "parent_id");
          final int _cursorIndexOfFundamentalTruth = CursorUtil.getColumnIndexOrThrow(_cursor, "fundamental_truth");
          final int _cursorIndexOfExperience = CursorUtil.getColumnIndexOrThrow(_cursor, "experience");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
          final PrincipleEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpUserId;
            _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            final String _tmpParentId;
            if (_cursor.isNull(_cursorIndexOfParentId)) {
              _tmpParentId = null;
            } else {
              _tmpParentId = _cursor.getString(_cursorIndexOfParentId);
            }
            final String _tmpFundamentalTruth;
            _tmpFundamentalTruth = _cursor.getString(_cursorIndexOfFundamentalTruth);
            final String _tmpExperience;
            if (_cursor.isNull(_cursorIndexOfExperience)) {
              _tmpExperience = null;
            } else {
              _tmpExperience = _cursor.getString(_cursorIndexOfExperience);
            }
            final Instant _tmpCreatedAt;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfCreatedAt);
            }
            final Instant _tmp_1 = __converters.toInstant(_tmp);
            if (_tmp_1 == null) {
              throw new IllegalStateException("Expected NON-NULL 'kotlinx.datetime.Instant', but it was NULL.");
            } else {
              _tmpCreatedAt = _tmp_1;
            }
            final Instant _tmpUpdatedAt;
            final Long _tmp_2;
            if (_cursor.isNull(_cursorIndexOfUpdatedAt)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getLong(_cursorIndexOfUpdatedAt);
            }
            final Instant _tmp_3 = __converters.toInstant(_tmp_2);
            if (_tmp_3 == null) {
              throw new IllegalStateException("Expected NON-NULL 'kotlinx.datetime.Instant', but it was NULL.");
            } else {
              _tmpUpdatedAt = _tmp_3;
            }
            _result = new PrincipleEntity(_tmpId,_tmpUserId,_tmpParentId,_tmpFundamentalTruth,_tmpExperience,_tmpCreatedAt,_tmpUpdatedAt);
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
