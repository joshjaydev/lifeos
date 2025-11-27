package com.lifeos.app.data.local;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.lifeos.app.data.local.dao.ActionCompletionDao;
import com.lifeos.app.data.local.dao.ActionCompletionDao_Impl;
import com.lifeos.app.data.local.dao.ActionDao;
import com.lifeos.app.data.local.dao.ActionDao_Impl;
import com.lifeos.app.data.local.dao.AiSuggestionDao;
import com.lifeos.app.data.local.dao.AiSuggestionDao_Impl;
import com.lifeos.app.data.local.dao.ChatMessageDao;
import com.lifeos.app.data.local.dao.ChatMessageDao_Impl;
import com.lifeos.app.data.local.dao.DeviceTokenDao;
import com.lifeos.app.data.local.dao.DeviceTokenDao_Impl;
import com.lifeos.app.data.local.dao.EventDao;
import com.lifeos.app.data.local.dao.EventDao_Impl;
import com.lifeos.app.data.local.dao.FolderDao;
import com.lifeos.app.data.local.dao.FolderDao_Impl;
import com.lifeos.app.data.local.dao.GoalDao;
import com.lifeos.app.data.local.dao.GoalDao_Impl;
import com.lifeos.app.data.local.dao.NoteDao;
import com.lifeos.app.data.local.dao.NoteDao_Impl;
import com.lifeos.app.data.local.dao.NotebookDao;
import com.lifeos.app.data.local.dao.NotebookDao_Impl;
import com.lifeos.app.data.local.dao.PrincipleDao;
import com.lifeos.app.data.local.dao.PrincipleDao_Impl;
import com.lifeos.app.data.local.dao.ProfileDao;
import com.lifeos.app.data.local.dao.ProfileDao_Impl;
import com.lifeos.app.data.local.dao.TimeBlockDao;
import com.lifeos.app.data.local.dao.TimeBlockDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class LifeOSDatabase_Impl extends LifeOSDatabase {
  private volatile ProfileDao _profileDao;

  private volatile FolderDao _folderDao;

  private volatile ActionDao _actionDao;

  private volatile ActionCompletionDao _actionCompletionDao;

  private volatile NotebookDao _notebookDao;

  private volatile NoteDao _noteDao;

  private volatile GoalDao _goalDao;

  private volatile PrincipleDao _principleDao;

  private volatile TimeBlockDao _timeBlockDao;

  private volatile EventDao _eventDao;

  private volatile ChatMessageDao _chatMessageDao;

  private volatile AiSuggestionDao _aiSuggestionDao;

  private volatile DeviceTokenDao _deviceTokenDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(2) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `profiles` (`id` TEXT NOT NULL, `email` TEXT, `display_name` TEXT, `avatar_url` TEXT, `time_block_size` INTEGER NOT NULL, `created_at` INTEGER NOT NULL, `updated_at` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `folders` (`id` TEXT NOT NULL, `user_id` TEXT NOT NULL, `name` TEXT NOT NULL, `color` INTEGER, `is_default` INTEGER NOT NULL, `goal_id` TEXT, `created_at` INTEGER NOT NULL, `updated_at` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `actions` (`id` TEXT NOT NULL, `user_id` TEXT NOT NULL, `folder_id` TEXT NOT NULL, `title` TEXT NOT NULL, `due_date` INTEGER, `due_time` TEXT, `is_recurring` INTEGER NOT NULL, `recurrence_type` TEXT, `notify_before` INTEGER NOT NULL, `created_at` INTEGER NOT NULL, `updated_at` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `action_completions` (`id` TEXT NOT NULL, `action_id` TEXT NOT NULL, `completed_date` TEXT NOT NULL, `completed_at` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `notebooks` (`id` TEXT NOT NULL, `user_id` TEXT NOT NULL, `name` TEXT NOT NULL, `is_default` INTEGER NOT NULL, `goal_id` TEXT, `created_at` INTEGER NOT NULL, `updated_at` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `notes` (`id` TEXT NOT NULL, `user_id` TEXT NOT NULL, `notebook_id` TEXT NOT NULL, `title` TEXT NOT NULL, `content` TEXT NOT NULL, `is_journal` INTEGER NOT NULL, `journal_date` TEXT, `created_at` INTEGER NOT NULL, `updated_at` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `goals` (`id` TEXT NOT NULL, `user_id` TEXT NOT NULL, `what_do_you_want` TEXT NOT NULL, `folder_id` TEXT, `notebook_id` TEXT, `created_at` INTEGER NOT NULL, `updated_at` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `principles` (`id` TEXT NOT NULL, `user_id` TEXT NOT NULL, `parent_id` TEXT, `fundamental_truth` TEXT NOT NULL, `experience` TEXT, `created_at` INTEGER NOT NULL, `updated_at` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `time_blocks` (`id` TEXT NOT NULL, `user_id` TEXT NOT NULL, `action_id` TEXT NOT NULL, `block_date` TEXT NOT NULL, `start_time` TEXT NOT NULL, `end_time` TEXT NOT NULL, `created_at` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `events` (`id` TEXT NOT NULL, `user_id` TEXT NOT NULL, `title` TEXT NOT NULL, `description` TEXT, `start_datetime` INTEGER NOT NULL, `end_datetime` INTEGER NOT NULL, `is_recurring` INTEGER NOT NULL, `recurrence_type` TEXT, `notify_before` INTEGER NOT NULL, `created_at` INTEGER NOT NULL, `updated_at` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `chat_messages` (`id` TEXT NOT NULL, `user_id` TEXT NOT NULL, `role` TEXT NOT NULL, `content` TEXT NOT NULL, `created_at` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `ai_suggestions` (`id` TEXT NOT NULL, `user_id` TEXT NOT NULL, `chat_message_id` TEXT NOT NULL, `suggestion_type` TEXT NOT NULL, `suggestion_data` TEXT NOT NULL, `status` TEXT NOT NULL, `created_at` INTEGER NOT NULL, `updated_at` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `device_tokens` (`id` TEXT NOT NULL, `user_id` TEXT NOT NULL, `token` TEXT NOT NULL, `platform` TEXT NOT NULL, `created_at` INTEGER NOT NULL, `updated_at` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '716d60a3d25ce1274bff88b31be81875')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `profiles`");
        db.execSQL("DROP TABLE IF EXISTS `folders`");
        db.execSQL("DROP TABLE IF EXISTS `actions`");
        db.execSQL("DROP TABLE IF EXISTS `action_completions`");
        db.execSQL("DROP TABLE IF EXISTS `notebooks`");
        db.execSQL("DROP TABLE IF EXISTS `notes`");
        db.execSQL("DROP TABLE IF EXISTS `goals`");
        db.execSQL("DROP TABLE IF EXISTS `principles`");
        db.execSQL("DROP TABLE IF EXISTS `time_blocks`");
        db.execSQL("DROP TABLE IF EXISTS `events`");
        db.execSQL("DROP TABLE IF EXISTS `chat_messages`");
        db.execSQL("DROP TABLE IF EXISTS `ai_suggestions`");
        db.execSQL("DROP TABLE IF EXISTS `device_tokens`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsProfiles = new HashMap<String, TableInfo.Column>(7);
        _columnsProfiles.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProfiles.put("email", new TableInfo.Column("email", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProfiles.put("display_name", new TableInfo.Column("display_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProfiles.put("avatar_url", new TableInfo.Column("avatar_url", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProfiles.put("time_block_size", new TableInfo.Column("time_block_size", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProfiles.put("created_at", new TableInfo.Column("created_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProfiles.put("updated_at", new TableInfo.Column("updated_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysProfiles = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesProfiles = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoProfiles = new TableInfo("profiles", _columnsProfiles, _foreignKeysProfiles, _indicesProfiles);
        final TableInfo _existingProfiles = TableInfo.read(db, "profiles");
        if (!_infoProfiles.equals(_existingProfiles)) {
          return new RoomOpenHelper.ValidationResult(false, "profiles(com.lifeos.app.data.local.entity.ProfileEntity).\n"
                  + " Expected:\n" + _infoProfiles + "\n"
                  + " Found:\n" + _existingProfiles);
        }
        final HashMap<String, TableInfo.Column> _columnsFolders = new HashMap<String, TableInfo.Column>(8);
        _columnsFolders.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFolders.put("user_id", new TableInfo.Column("user_id", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFolders.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFolders.put("color", new TableInfo.Column("color", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFolders.put("is_default", new TableInfo.Column("is_default", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFolders.put("goal_id", new TableInfo.Column("goal_id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFolders.put("created_at", new TableInfo.Column("created_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFolders.put("updated_at", new TableInfo.Column("updated_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysFolders = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesFolders = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoFolders = new TableInfo("folders", _columnsFolders, _foreignKeysFolders, _indicesFolders);
        final TableInfo _existingFolders = TableInfo.read(db, "folders");
        if (!_infoFolders.equals(_existingFolders)) {
          return new RoomOpenHelper.ValidationResult(false, "folders(com.lifeos.app.data.local.entity.FolderEntity).\n"
                  + " Expected:\n" + _infoFolders + "\n"
                  + " Found:\n" + _existingFolders);
        }
        final HashMap<String, TableInfo.Column> _columnsActions = new HashMap<String, TableInfo.Column>(11);
        _columnsActions.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsActions.put("user_id", new TableInfo.Column("user_id", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsActions.put("folder_id", new TableInfo.Column("folder_id", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsActions.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsActions.put("due_date", new TableInfo.Column("due_date", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsActions.put("due_time", new TableInfo.Column("due_time", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsActions.put("is_recurring", new TableInfo.Column("is_recurring", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsActions.put("recurrence_type", new TableInfo.Column("recurrence_type", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsActions.put("notify_before", new TableInfo.Column("notify_before", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsActions.put("created_at", new TableInfo.Column("created_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsActions.put("updated_at", new TableInfo.Column("updated_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysActions = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesActions = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoActions = new TableInfo("actions", _columnsActions, _foreignKeysActions, _indicesActions);
        final TableInfo _existingActions = TableInfo.read(db, "actions");
        if (!_infoActions.equals(_existingActions)) {
          return new RoomOpenHelper.ValidationResult(false, "actions(com.lifeos.app.data.local.entity.ActionEntity).\n"
                  + " Expected:\n" + _infoActions + "\n"
                  + " Found:\n" + _existingActions);
        }
        final HashMap<String, TableInfo.Column> _columnsActionCompletions = new HashMap<String, TableInfo.Column>(4);
        _columnsActionCompletions.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsActionCompletions.put("action_id", new TableInfo.Column("action_id", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsActionCompletions.put("completed_date", new TableInfo.Column("completed_date", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsActionCompletions.put("completed_at", new TableInfo.Column("completed_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysActionCompletions = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesActionCompletions = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoActionCompletions = new TableInfo("action_completions", _columnsActionCompletions, _foreignKeysActionCompletions, _indicesActionCompletions);
        final TableInfo _existingActionCompletions = TableInfo.read(db, "action_completions");
        if (!_infoActionCompletions.equals(_existingActionCompletions)) {
          return new RoomOpenHelper.ValidationResult(false, "action_completions(com.lifeos.app.data.local.entity.ActionCompletionEntity).\n"
                  + " Expected:\n" + _infoActionCompletions + "\n"
                  + " Found:\n" + _existingActionCompletions);
        }
        final HashMap<String, TableInfo.Column> _columnsNotebooks = new HashMap<String, TableInfo.Column>(7);
        _columnsNotebooks.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNotebooks.put("user_id", new TableInfo.Column("user_id", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNotebooks.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNotebooks.put("is_default", new TableInfo.Column("is_default", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNotebooks.put("goal_id", new TableInfo.Column("goal_id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNotebooks.put("created_at", new TableInfo.Column("created_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNotebooks.put("updated_at", new TableInfo.Column("updated_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysNotebooks = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesNotebooks = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoNotebooks = new TableInfo("notebooks", _columnsNotebooks, _foreignKeysNotebooks, _indicesNotebooks);
        final TableInfo _existingNotebooks = TableInfo.read(db, "notebooks");
        if (!_infoNotebooks.equals(_existingNotebooks)) {
          return new RoomOpenHelper.ValidationResult(false, "notebooks(com.lifeos.app.data.local.entity.NotebookEntity).\n"
                  + " Expected:\n" + _infoNotebooks + "\n"
                  + " Found:\n" + _existingNotebooks);
        }
        final HashMap<String, TableInfo.Column> _columnsNotes = new HashMap<String, TableInfo.Column>(9);
        _columnsNotes.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNotes.put("user_id", new TableInfo.Column("user_id", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNotes.put("notebook_id", new TableInfo.Column("notebook_id", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNotes.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNotes.put("content", new TableInfo.Column("content", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNotes.put("is_journal", new TableInfo.Column("is_journal", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNotes.put("journal_date", new TableInfo.Column("journal_date", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNotes.put("created_at", new TableInfo.Column("created_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNotes.put("updated_at", new TableInfo.Column("updated_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysNotes = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesNotes = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoNotes = new TableInfo("notes", _columnsNotes, _foreignKeysNotes, _indicesNotes);
        final TableInfo _existingNotes = TableInfo.read(db, "notes");
        if (!_infoNotes.equals(_existingNotes)) {
          return new RoomOpenHelper.ValidationResult(false, "notes(com.lifeos.app.data.local.entity.NoteEntity).\n"
                  + " Expected:\n" + _infoNotes + "\n"
                  + " Found:\n" + _existingNotes);
        }
        final HashMap<String, TableInfo.Column> _columnsGoals = new HashMap<String, TableInfo.Column>(7);
        _columnsGoals.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGoals.put("user_id", new TableInfo.Column("user_id", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGoals.put("what_do_you_want", new TableInfo.Column("what_do_you_want", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGoals.put("folder_id", new TableInfo.Column("folder_id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGoals.put("notebook_id", new TableInfo.Column("notebook_id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGoals.put("created_at", new TableInfo.Column("created_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGoals.put("updated_at", new TableInfo.Column("updated_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysGoals = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesGoals = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoGoals = new TableInfo("goals", _columnsGoals, _foreignKeysGoals, _indicesGoals);
        final TableInfo _existingGoals = TableInfo.read(db, "goals");
        if (!_infoGoals.equals(_existingGoals)) {
          return new RoomOpenHelper.ValidationResult(false, "goals(com.lifeos.app.data.local.entity.GoalEntity).\n"
                  + " Expected:\n" + _infoGoals + "\n"
                  + " Found:\n" + _existingGoals);
        }
        final HashMap<String, TableInfo.Column> _columnsPrinciples = new HashMap<String, TableInfo.Column>(7);
        _columnsPrinciples.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPrinciples.put("user_id", new TableInfo.Column("user_id", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPrinciples.put("parent_id", new TableInfo.Column("parent_id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPrinciples.put("fundamental_truth", new TableInfo.Column("fundamental_truth", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPrinciples.put("experience", new TableInfo.Column("experience", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPrinciples.put("created_at", new TableInfo.Column("created_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPrinciples.put("updated_at", new TableInfo.Column("updated_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPrinciples = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPrinciples = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPrinciples = new TableInfo("principles", _columnsPrinciples, _foreignKeysPrinciples, _indicesPrinciples);
        final TableInfo _existingPrinciples = TableInfo.read(db, "principles");
        if (!_infoPrinciples.equals(_existingPrinciples)) {
          return new RoomOpenHelper.ValidationResult(false, "principles(com.lifeos.app.data.local.entity.PrincipleEntity).\n"
                  + " Expected:\n" + _infoPrinciples + "\n"
                  + " Found:\n" + _existingPrinciples);
        }
        final HashMap<String, TableInfo.Column> _columnsTimeBlocks = new HashMap<String, TableInfo.Column>(7);
        _columnsTimeBlocks.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTimeBlocks.put("user_id", new TableInfo.Column("user_id", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTimeBlocks.put("action_id", new TableInfo.Column("action_id", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTimeBlocks.put("block_date", new TableInfo.Column("block_date", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTimeBlocks.put("start_time", new TableInfo.Column("start_time", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTimeBlocks.put("end_time", new TableInfo.Column("end_time", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTimeBlocks.put("created_at", new TableInfo.Column("created_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTimeBlocks = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTimeBlocks = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTimeBlocks = new TableInfo("time_blocks", _columnsTimeBlocks, _foreignKeysTimeBlocks, _indicesTimeBlocks);
        final TableInfo _existingTimeBlocks = TableInfo.read(db, "time_blocks");
        if (!_infoTimeBlocks.equals(_existingTimeBlocks)) {
          return new RoomOpenHelper.ValidationResult(false, "time_blocks(com.lifeos.app.data.local.entity.TimeBlockEntity).\n"
                  + " Expected:\n" + _infoTimeBlocks + "\n"
                  + " Found:\n" + _existingTimeBlocks);
        }
        final HashMap<String, TableInfo.Column> _columnsEvents = new HashMap<String, TableInfo.Column>(11);
        _columnsEvents.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEvents.put("user_id", new TableInfo.Column("user_id", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEvents.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEvents.put("description", new TableInfo.Column("description", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEvents.put("start_datetime", new TableInfo.Column("start_datetime", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEvents.put("end_datetime", new TableInfo.Column("end_datetime", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEvents.put("is_recurring", new TableInfo.Column("is_recurring", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEvents.put("recurrence_type", new TableInfo.Column("recurrence_type", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEvents.put("notify_before", new TableInfo.Column("notify_before", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEvents.put("created_at", new TableInfo.Column("created_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEvents.put("updated_at", new TableInfo.Column("updated_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysEvents = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesEvents = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoEvents = new TableInfo("events", _columnsEvents, _foreignKeysEvents, _indicesEvents);
        final TableInfo _existingEvents = TableInfo.read(db, "events");
        if (!_infoEvents.equals(_existingEvents)) {
          return new RoomOpenHelper.ValidationResult(false, "events(com.lifeos.app.data.local.entity.EventEntity).\n"
                  + " Expected:\n" + _infoEvents + "\n"
                  + " Found:\n" + _existingEvents);
        }
        final HashMap<String, TableInfo.Column> _columnsChatMessages = new HashMap<String, TableInfo.Column>(5);
        _columnsChatMessages.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsChatMessages.put("user_id", new TableInfo.Column("user_id", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsChatMessages.put("role", new TableInfo.Column("role", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsChatMessages.put("content", new TableInfo.Column("content", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsChatMessages.put("created_at", new TableInfo.Column("created_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysChatMessages = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesChatMessages = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoChatMessages = new TableInfo("chat_messages", _columnsChatMessages, _foreignKeysChatMessages, _indicesChatMessages);
        final TableInfo _existingChatMessages = TableInfo.read(db, "chat_messages");
        if (!_infoChatMessages.equals(_existingChatMessages)) {
          return new RoomOpenHelper.ValidationResult(false, "chat_messages(com.lifeos.app.data.local.entity.ChatMessageEntity).\n"
                  + " Expected:\n" + _infoChatMessages + "\n"
                  + " Found:\n" + _existingChatMessages);
        }
        final HashMap<String, TableInfo.Column> _columnsAiSuggestions = new HashMap<String, TableInfo.Column>(8);
        _columnsAiSuggestions.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAiSuggestions.put("user_id", new TableInfo.Column("user_id", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAiSuggestions.put("chat_message_id", new TableInfo.Column("chat_message_id", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAiSuggestions.put("suggestion_type", new TableInfo.Column("suggestion_type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAiSuggestions.put("suggestion_data", new TableInfo.Column("suggestion_data", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAiSuggestions.put("status", new TableInfo.Column("status", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAiSuggestions.put("created_at", new TableInfo.Column("created_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAiSuggestions.put("updated_at", new TableInfo.Column("updated_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAiSuggestions = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesAiSuggestions = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoAiSuggestions = new TableInfo("ai_suggestions", _columnsAiSuggestions, _foreignKeysAiSuggestions, _indicesAiSuggestions);
        final TableInfo _existingAiSuggestions = TableInfo.read(db, "ai_suggestions");
        if (!_infoAiSuggestions.equals(_existingAiSuggestions)) {
          return new RoomOpenHelper.ValidationResult(false, "ai_suggestions(com.lifeos.app.data.local.entity.AiSuggestionEntity).\n"
                  + " Expected:\n" + _infoAiSuggestions + "\n"
                  + " Found:\n" + _existingAiSuggestions);
        }
        final HashMap<String, TableInfo.Column> _columnsDeviceTokens = new HashMap<String, TableInfo.Column>(6);
        _columnsDeviceTokens.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDeviceTokens.put("user_id", new TableInfo.Column("user_id", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDeviceTokens.put("token", new TableInfo.Column("token", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDeviceTokens.put("platform", new TableInfo.Column("platform", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDeviceTokens.put("created_at", new TableInfo.Column("created_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDeviceTokens.put("updated_at", new TableInfo.Column("updated_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysDeviceTokens = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesDeviceTokens = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoDeviceTokens = new TableInfo("device_tokens", _columnsDeviceTokens, _foreignKeysDeviceTokens, _indicesDeviceTokens);
        final TableInfo _existingDeviceTokens = TableInfo.read(db, "device_tokens");
        if (!_infoDeviceTokens.equals(_existingDeviceTokens)) {
          return new RoomOpenHelper.ValidationResult(false, "device_tokens(com.lifeos.app.data.local.entity.DeviceTokenEntity).\n"
                  + " Expected:\n" + _infoDeviceTokens + "\n"
                  + " Found:\n" + _existingDeviceTokens);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "716d60a3d25ce1274bff88b31be81875", "17345e65dfb22621d425ec06b764597f");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "profiles","folders","actions","action_completions","notebooks","notes","goals","principles","time_blocks","events","chat_messages","ai_suggestions","device_tokens");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `profiles`");
      _db.execSQL("DELETE FROM `folders`");
      _db.execSQL("DELETE FROM `actions`");
      _db.execSQL("DELETE FROM `action_completions`");
      _db.execSQL("DELETE FROM `notebooks`");
      _db.execSQL("DELETE FROM `notes`");
      _db.execSQL("DELETE FROM `goals`");
      _db.execSQL("DELETE FROM `principles`");
      _db.execSQL("DELETE FROM `time_blocks`");
      _db.execSQL("DELETE FROM `events`");
      _db.execSQL("DELETE FROM `chat_messages`");
      _db.execSQL("DELETE FROM `ai_suggestions`");
      _db.execSQL("DELETE FROM `device_tokens`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(ProfileDao.class, ProfileDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(FolderDao.class, FolderDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ActionDao.class, ActionDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ActionCompletionDao.class, ActionCompletionDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(NotebookDao.class, NotebookDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(NoteDao.class, NoteDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(GoalDao.class, GoalDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(PrincipleDao.class, PrincipleDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(TimeBlockDao.class, TimeBlockDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(EventDao.class, EventDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ChatMessageDao.class, ChatMessageDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(AiSuggestionDao.class, AiSuggestionDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(DeviceTokenDao.class, DeviceTokenDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public ProfileDao profileDao() {
    if (_profileDao != null) {
      return _profileDao;
    } else {
      synchronized(this) {
        if(_profileDao == null) {
          _profileDao = new ProfileDao_Impl(this);
        }
        return _profileDao;
      }
    }
  }

  @Override
  public FolderDao folderDao() {
    if (_folderDao != null) {
      return _folderDao;
    } else {
      synchronized(this) {
        if(_folderDao == null) {
          _folderDao = new FolderDao_Impl(this);
        }
        return _folderDao;
      }
    }
  }

  @Override
  public ActionDao actionDao() {
    if (_actionDao != null) {
      return _actionDao;
    } else {
      synchronized(this) {
        if(_actionDao == null) {
          _actionDao = new ActionDao_Impl(this);
        }
        return _actionDao;
      }
    }
  }

  @Override
  public ActionCompletionDao actionCompletionDao() {
    if (_actionCompletionDao != null) {
      return _actionCompletionDao;
    } else {
      synchronized(this) {
        if(_actionCompletionDao == null) {
          _actionCompletionDao = new ActionCompletionDao_Impl(this);
        }
        return _actionCompletionDao;
      }
    }
  }

  @Override
  public NotebookDao notebookDao() {
    if (_notebookDao != null) {
      return _notebookDao;
    } else {
      synchronized(this) {
        if(_notebookDao == null) {
          _notebookDao = new NotebookDao_Impl(this);
        }
        return _notebookDao;
      }
    }
  }

  @Override
  public NoteDao noteDao() {
    if (_noteDao != null) {
      return _noteDao;
    } else {
      synchronized(this) {
        if(_noteDao == null) {
          _noteDao = new NoteDao_Impl(this);
        }
        return _noteDao;
      }
    }
  }

  @Override
  public GoalDao goalDao() {
    if (_goalDao != null) {
      return _goalDao;
    } else {
      synchronized(this) {
        if(_goalDao == null) {
          _goalDao = new GoalDao_Impl(this);
        }
        return _goalDao;
      }
    }
  }

  @Override
  public PrincipleDao principleDao() {
    if (_principleDao != null) {
      return _principleDao;
    } else {
      synchronized(this) {
        if(_principleDao == null) {
          _principleDao = new PrincipleDao_Impl(this);
        }
        return _principleDao;
      }
    }
  }

  @Override
  public TimeBlockDao timeBlockDao() {
    if (_timeBlockDao != null) {
      return _timeBlockDao;
    } else {
      synchronized(this) {
        if(_timeBlockDao == null) {
          _timeBlockDao = new TimeBlockDao_Impl(this);
        }
        return _timeBlockDao;
      }
    }
  }

  @Override
  public EventDao eventDao() {
    if (_eventDao != null) {
      return _eventDao;
    } else {
      synchronized(this) {
        if(_eventDao == null) {
          _eventDao = new EventDao_Impl(this);
        }
        return _eventDao;
      }
    }
  }

  @Override
  public ChatMessageDao chatMessageDao() {
    if (_chatMessageDao != null) {
      return _chatMessageDao;
    } else {
      synchronized(this) {
        if(_chatMessageDao == null) {
          _chatMessageDao = new ChatMessageDao_Impl(this);
        }
        return _chatMessageDao;
      }
    }
  }

  @Override
  public AiSuggestionDao aiSuggestionDao() {
    if (_aiSuggestionDao != null) {
      return _aiSuggestionDao;
    } else {
      synchronized(this) {
        if(_aiSuggestionDao == null) {
          _aiSuggestionDao = new AiSuggestionDao_Impl(this);
        }
        return _aiSuggestionDao;
      }
    }
  }

  @Override
  public DeviceTokenDao deviceTokenDao() {
    if (_deviceTokenDao != null) {
      return _deviceTokenDao;
    } else {
      synchronized(this) {
        if(_deviceTokenDao == null) {
          _deviceTokenDao = new DeviceTokenDao_Impl(this);
        }
        return _deviceTokenDao;
      }
    }
  }
}
