package com.lifeos.app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.lifeos.app.data.local.dao.*
import com.lifeos.app.data.local.entity.*

@Database(
    entities = [
        ProfileEntity::class,
        FolderEntity::class,
        ActionEntity::class,
        ActionCompletionEntity::class,
        NotebookEntity::class,
        NoteEntity::class,
        GoalEntity::class,
        PrincipleEntity::class,
        TimeBlockEntity::class,
        EventEntity::class,
        ChatMessageEntity::class,
        AiSuggestionEntity::class,
        DeviceTokenEntity::class
    ],
    version = 2,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class LifeOSDatabase : RoomDatabase() {
    abstract fun profileDao(): ProfileDao
    abstract fun folderDao(): FolderDao
    abstract fun actionDao(): ActionDao
    abstract fun actionCompletionDao(): ActionCompletionDao
    abstract fun notebookDao(): NotebookDao
    abstract fun noteDao(): NoteDao
    abstract fun goalDao(): GoalDao
    abstract fun principleDao(): PrincipleDao
    abstract fun timeBlockDao(): TimeBlockDao
    abstract fun eventDao(): EventDao
    abstract fun chatMessageDao(): ChatMessageDao
    abstract fun aiSuggestionDao(): AiSuggestionDao
    abstract fun deviceTokenDao(): DeviceTokenDao
}
