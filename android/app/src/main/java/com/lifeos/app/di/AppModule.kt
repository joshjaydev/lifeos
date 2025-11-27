package com.lifeos.app.di

import android.content.Context
import androidx.room.Room
import com.lifeos.app.BuildConfig
import com.lifeos.app.data.local.LifeOSDatabase
import com.lifeos.app.data.local.dao.*
import com.lifeos.data.local.AndroidLocalDatabase
import com.lifeos.data.local.LocalDatabase
import com.lifeos.data.repository.*
import com.lifeos.domain.repository.*
import com.lifeos.domain.usecase.action.*
import com.lifeos.domain.usecase.folder.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.realtime.Realtime
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSupabaseClient(): SupabaseClient {
        return createSupabaseClient(
            supabaseUrl = BuildConfig.SUPABASE_URL,
            supabaseKey = BuildConfig.SUPABASE_ANON_KEY
        ) {
            install(Auth)
            install(Postgrest)
            install(Realtime)
        }
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): LifeOSDatabase {
        return Room.databaseBuilder(
            context,
            LifeOSDatabase::class.java,
            "lifeos_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideProfileDao(database: LifeOSDatabase): ProfileDao = database.profileDao()

    @Provides
    fun provideFolderDao(database: LifeOSDatabase): FolderDao = database.folderDao()

    @Provides
    fun provideActionDao(database: LifeOSDatabase): ActionDao = database.actionDao()

    @Provides
    fun provideNotebookDao(database: LifeOSDatabase): NotebookDao = database.notebookDao()

    @Provides
    fun provideNoteDao(database: LifeOSDatabase): NoteDao = database.noteDao()

    @Provides
    fun provideGoalDao(database: LifeOSDatabase): GoalDao = database.goalDao()

    @Provides
    fun providePrincipleDao(database: LifeOSDatabase): PrincipleDao = database.principleDao()

    @Provides
    fun provideTimeBlockDao(database: LifeOSDatabase): TimeBlockDao = database.timeBlockDao()

    @Provides
    fun provideEventDao(database: LifeOSDatabase): EventDao = database.eventDao()

    @Provides
    fun provideChatMessageDao(database: LifeOSDatabase): ChatMessageDao = database.chatMessageDao()

    @Provides
    fun provideAiSuggestionDao(database: LifeOSDatabase): AiSuggestionDao = database.aiSuggestionDao()

    @Provides
    fun provideDeviceTokenDao(database: LifeOSDatabase): DeviceTokenDao = database.deviceTokenDao()

    @Provides
    fun provideActionCompletionDao(database: LifeOSDatabase): ActionCompletionDao = database.actionCompletionDao()

    // LocalDatabase
    @Provides
    @Singleton
    fun provideLocalDatabase(database: LifeOSDatabase): LocalDatabase {
        return AndroidLocalDatabase(database)
    }

    // Repositories
    @Provides
    @Singleton
    fun provideActionRepository(localDatabase: LocalDatabase): ActionRepository {
        return ActionRepositoryImpl(localDatabase)
    }

    @Provides
    @Singleton
    fun provideFolderRepository(localDatabase: LocalDatabase): FolderRepository {
        return FolderRepositoryImpl(localDatabase)
    }

    @Provides
    @Singleton
    fun provideNoteRepository(localDatabase: LocalDatabase): NoteRepository {
        return NoteRepositoryImpl(localDatabase)
    }

    @Provides
    @Singleton
    fun provideNotebookRepository(localDatabase: LocalDatabase): NotebookRepository {
        return NotebookRepositoryImpl(localDatabase)
    }

    @Provides
    @Singleton
    fun provideGoalRepository(localDatabase: LocalDatabase): GoalRepository {
        return GoalRepositoryImpl(localDatabase)
    }

    @Provides
    @Singleton
    fun providePrincipleRepository(localDatabase: LocalDatabase): PrincipleRepository {
        return PrincipleRepositoryImpl(localDatabase)
    }

    @Provides
    @Singleton
    fun provideTimeBlockRepository(localDatabase: LocalDatabase): TimeBlockRepository {
        return TimeBlockRepositoryImpl(localDatabase)
    }

    @Provides
    @Singleton
    fun provideEventRepository(localDatabase: LocalDatabase): EventRepository {
        return EventRepositoryImpl(localDatabase)
    }

    @Provides
    @Singleton
    fun provideChatRepository(localDatabase: LocalDatabase): ChatRepository {
        return ChatRepositoryImpl(localDatabase)
    }

    @Provides
    @Singleton
    fun provideAiSuggestionRepository(localDatabase: LocalDatabase): AiSuggestionRepository {
        return AiSuggestionRepositoryImpl(localDatabase)
    }

    @Provides
    @Singleton
    fun provideProfileRepository(localDatabase: LocalDatabase): ProfileRepository {
        return ProfileRepositoryImpl(localDatabase)
    }

    // Action Use Cases
    @Provides
    fun provideGetActionsUseCase(actionRepository: ActionRepository): GetActionsUseCase {
        return GetActionsUseCase(actionRepository)
    }

    @Provides
    fun provideGetActionsByFolderUseCase(actionRepository: ActionRepository): GetActionsByFolderUseCase {
        return GetActionsByFolderUseCase(actionRepository)
    }

    @Provides
    fun provideCreateActionUseCase(actionRepository: ActionRepository): CreateActionUseCase {
        return CreateActionUseCase(actionRepository)
    }

    @Provides
    fun provideUpdateActionUseCase(actionRepository: ActionRepository): UpdateActionUseCase {
        return UpdateActionUseCase(actionRepository)
    }

    @Provides
    fun provideDeleteActionUseCase(actionRepository: ActionRepository): DeleteActionUseCase {
        return DeleteActionUseCase(actionRepository)
    }

    @Provides
    fun provideCompleteActionUseCase(actionRepository: ActionRepository): CompleteActionUseCase {
        return CompleteActionUseCase(actionRepository)
    }

    // Folder Use Cases
    @Provides
    fun provideGetFoldersUseCase(folderRepository: FolderRepository): GetFoldersUseCase {
        return GetFoldersUseCase(folderRepository)
    }

    @Provides
    fun provideCreateFolderUseCase(folderRepository: FolderRepository): CreateFolderUseCase {
        return CreateFolderUseCase(folderRepository)
    }

    @Provides
    fun provideUpdateFolderUseCase(folderRepository: FolderRepository): UpdateFolderUseCase {
        return UpdateFolderUseCase(folderRepository)
    }

    @Provides
    fun provideDeleteFolderUseCase(folderRepository: FolderRepository): DeleteFolderUseCase {
        return DeleteFolderUseCase(folderRepository)
    }
}
