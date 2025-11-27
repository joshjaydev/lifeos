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
import com.lifeos.app.data.local.entity.AiSuggestionEntity;
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
public final class AiSuggestionDao_Impl implements AiSuggestionDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<AiSuggestionEntity> __insertionAdapterOfAiSuggestionEntity;

  private final Converters __converters = new Converters();

  private final EntityDeletionOrUpdateAdapter<AiSuggestionEntity> __updateAdapterOfAiSuggestionEntity;

  private final SharedSQLiteStatement __preparedStmtOfDelete;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public AiSuggestionDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfAiSuggestionEntity = new EntityInsertionAdapter<AiSuggestionEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `ai_suggestions` (`id`,`user_id`,`chat_message_id`,`suggestion_type`,`suggestion_data`,`status`,`created_at`,`updated_at`) VALUES (?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final AiSuggestionEntity entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getUserId());
        statement.bindString(3, entity.getChatMessageId());
        statement.bindString(4, entity.getSuggestionType());
        statement.bindString(5, entity.getSuggestionData());
        statement.bindString(6, entity.getStatus());
        final Long _tmp = __converters.fromInstant(entity.getCreatedAt());
        if (_tmp == null) {
          statement.bindNull(7);
        } else {
          statement.bindLong(7, _tmp);
        }
        final Long _tmp_1 = __converters.fromInstant(entity.getUpdatedAt());
        if (_tmp_1 == null) {
          statement.bindNull(8);
        } else {
          statement.bindLong(8, _tmp_1);
        }
      }
    };
    this.__updateAdapterOfAiSuggestionEntity = new EntityDeletionOrUpdateAdapter<AiSuggestionEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `ai_suggestions` SET `id` = ?,`user_id` = ?,`chat_message_id` = ?,`suggestion_type` = ?,`suggestion_data` = ?,`status` = ?,`created_at` = ?,`updated_at` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final AiSuggestionEntity entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getUserId());
        statement.bindString(3, entity.getChatMessageId());
        statement.bindString(4, entity.getSuggestionType());
        statement.bindString(5, entity.getSuggestionData());
        statement.bindString(6, entity.getStatus());
        final Long _tmp = __converters.fromInstant(entity.getCreatedAt());
        if (_tmp == null) {
          statement.bindNull(7);
        } else {
          statement.bindLong(7, _tmp);
        }
        final Long _tmp_1 = __converters.fromInstant(entity.getUpdatedAt());
        if (_tmp_1 == null) {
          statement.bindNull(8);
        } else {
          statement.bindLong(8, _tmp_1);
        }
        statement.bindString(9, entity.getId());
      }
    };
    this.__preparedStmtOfDelete = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM ai_suggestions WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM ai_suggestions WHERE user_id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final AiSuggestionEntity suggestion,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfAiSuggestionEntity.insert(suggestion);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertAll(final List<AiSuggestionEntity> suggestions,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfAiSuggestionEntity.insert(suggestions);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final AiSuggestionEntity suggestion,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfAiSuggestionEntity.handle(suggestion);
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
  public Flow<List<AiSuggestionEntity>> getSuggestions(final String userId) {
    final String _sql = "SELECT * FROM ai_suggestions WHERE user_id = ? ORDER BY created_at DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, userId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"ai_suggestions"}, new Callable<List<AiSuggestionEntity>>() {
      @Override
      @NonNull
      public List<AiSuggestionEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "user_id");
          final int _cursorIndexOfChatMessageId = CursorUtil.getColumnIndexOrThrow(_cursor, "chat_message_id");
          final int _cursorIndexOfSuggestionType = CursorUtil.getColumnIndexOrThrow(_cursor, "suggestion_type");
          final int _cursorIndexOfSuggestionData = CursorUtil.getColumnIndexOrThrow(_cursor, "suggestion_data");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
          final List<AiSuggestionEntity> _result = new ArrayList<AiSuggestionEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AiSuggestionEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpUserId;
            _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            final String _tmpChatMessageId;
            _tmpChatMessageId = _cursor.getString(_cursorIndexOfChatMessageId);
            final String _tmpSuggestionType;
            _tmpSuggestionType = _cursor.getString(_cursorIndexOfSuggestionType);
            final String _tmpSuggestionData;
            _tmpSuggestionData = _cursor.getString(_cursorIndexOfSuggestionData);
            final String _tmpStatus;
            _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
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
            _item = new AiSuggestionEntity(_tmpId,_tmpUserId,_tmpChatMessageId,_tmpSuggestionType,_tmpSuggestionData,_tmpStatus,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Flow<List<AiSuggestionEntity>> getPendingSuggestions(final String userId) {
    final String _sql = "SELECT * FROM ai_suggestions WHERE user_id = ? AND status = 'PENDING' ORDER BY created_at DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, userId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"ai_suggestions"}, new Callable<List<AiSuggestionEntity>>() {
      @Override
      @NonNull
      public List<AiSuggestionEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "user_id");
          final int _cursorIndexOfChatMessageId = CursorUtil.getColumnIndexOrThrow(_cursor, "chat_message_id");
          final int _cursorIndexOfSuggestionType = CursorUtil.getColumnIndexOrThrow(_cursor, "suggestion_type");
          final int _cursorIndexOfSuggestionData = CursorUtil.getColumnIndexOrThrow(_cursor, "suggestion_data");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
          final List<AiSuggestionEntity> _result = new ArrayList<AiSuggestionEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AiSuggestionEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpUserId;
            _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            final String _tmpChatMessageId;
            _tmpChatMessageId = _cursor.getString(_cursorIndexOfChatMessageId);
            final String _tmpSuggestionType;
            _tmpSuggestionType = _cursor.getString(_cursorIndexOfSuggestionType);
            final String _tmpSuggestionData;
            _tmpSuggestionData = _cursor.getString(_cursorIndexOfSuggestionData);
            final String _tmpStatus;
            _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
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
            _item = new AiSuggestionEntity(_tmpId,_tmpUserId,_tmpChatMessageId,_tmpSuggestionType,_tmpSuggestionData,_tmpStatus,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Flow<List<AiSuggestionEntity>> getSuggestionsByMessage(final String messageId) {
    final String _sql = "SELECT * FROM ai_suggestions WHERE chat_message_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, messageId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"ai_suggestions"}, new Callable<List<AiSuggestionEntity>>() {
      @Override
      @NonNull
      public List<AiSuggestionEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "user_id");
          final int _cursorIndexOfChatMessageId = CursorUtil.getColumnIndexOrThrow(_cursor, "chat_message_id");
          final int _cursorIndexOfSuggestionType = CursorUtil.getColumnIndexOrThrow(_cursor, "suggestion_type");
          final int _cursorIndexOfSuggestionData = CursorUtil.getColumnIndexOrThrow(_cursor, "suggestion_data");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
          final List<AiSuggestionEntity> _result = new ArrayList<AiSuggestionEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AiSuggestionEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpUserId;
            _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            final String _tmpChatMessageId;
            _tmpChatMessageId = _cursor.getString(_cursorIndexOfChatMessageId);
            final String _tmpSuggestionType;
            _tmpSuggestionType = _cursor.getString(_cursorIndexOfSuggestionType);
            final String _tmpSuggestionData;
            _tmpSuggestionData = _cursor.getString(_cursorIndexOfSuggestionData);
            final String _tmpStatus;
            _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
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
            _item = new AiSuggestionEntity(_tmpId,_tmpUserId,_tmpChatMessageId,_tmpSuggestionType,_tmpSuggestionData,_tmpStatus,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Flow<AiSuggestionEntity> getSuggestion(final String id) {
    final String _sql = "SELECT * FROM ai_suggestions WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, id);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"ai_suggestions"}, new Callable<AiSuggestionEntity>() {
      @Override
      @Nullable
      public AiSuggestionEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "user_id");
          final int _cursorIndexOfChatMessageId = CursorUtil.getColumnIndexOrThrow(_cursor, "chat_message_id");
          final int _cursorIndexOfSuggestionType = CursorUtil.getColumnIndexOrThrow(_cursor, "suggestion_type");
          final int _cursorIndexOfSuggestionData = CursorUtil.getColumnIndexOrThrow(_cursor, "suggestion_data");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
          final AiSuggestionEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpUserId;
            _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            final String _tmpChatMessageId;
            _tmpChatMessageId = _cursor.getString(_cursorIndexOfChatMessageId);
            final String _tmpSuggestionType;
            _tmpSuggestionType = _cursor.getString(_cursorIndexOfSuggestionType);
            final String _tmpSuggestionData;
            _tmpSuggestionData = _cursor.getString(_cursorIndexOfSuggestionData);
            final String _tmpStatus;
            _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
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
            _result = new AiSuggestionEntity(_tmpId,_tmpUserId,_tmpChatMessageId,_tmpSuggestionType,_tmpSuggestionData,_tmpStatus,_tmpCreatedAt,_tmpUpdatedAt);
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
