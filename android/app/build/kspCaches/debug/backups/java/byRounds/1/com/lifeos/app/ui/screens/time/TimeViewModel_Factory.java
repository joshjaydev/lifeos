package com.lifeos.app.ui.screens.time;

import android.content.Context;
import com.lifeos.domain.repository.EventRepository;
import com.lifeos.domain.repository.TimeBlockRepository;
import com.lifeos.domain.usecase.action.CompleteActionUseCase;
import com.lifeos.domain.usecase.action.GetActionsUseCase;
import com.lifeos.domain.usecase.folder.GetFoldersUseCase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class TimeViewModel_Factory implements Factory<TimeViewModel> {
  private final Provider<Context> contextProvider;

  private final Provider<GetActionsUseCase> getActionsUseCaseProvider;

  private final Provider<GetFoldersUseCase> getFoldersUseCaseProvider;

  private final Provider<CompleteActionUseCase> completeActionUseCaseProvider;

  private final Provider<TimeBlockRepository> timeBlockRepositoryProvider;

  private final Provider<EventRepository> eventRepositoryProvider;

  public TimeViewModel_Factory(Provider<Context> contextProvider,
      Provider<GetActionsUseCase> getActionsUseCaseProvider,
      Provider<GetFoldersUseCase> getFoldersUseCaseProvider,
      Provider<CompleteActionUseCase> completeActionUseCaseProvider,
      Provider<TimeBlockRepository> timeBlockRepositoryProvider,
      Provider<EventRepository> eventRepositoryProvider) {
    this.contextProvider = contextProvider;
    this.getActionsUseCaseProvider = getActionsUseCaseProvider;
    this.getFoldersUseCaseProvider = getFoldersUseCaseProvider;
    this.completeActionUseCaseProvider = completeActionUseCaseProvider;
    this.timeBlockRepositoryProvider = timeBlockRepositoryProvider;
    this.eventRepositoryProvider = eventRepositoryProvider;
  }

  @Override
  public TimeViewModel get() {
    return newInstance(contextProvider.get(), getActionsUseCaseProvider.get(), getFoldersUseCaseProvider.get(), completeActionUseCaseProvider.get(), timeBlockRepositoryProvider.get(), eventRepositoryProvider.get());
  }

  public static TimeViewModel_Factory create(Provider<Context> contextProvider,
      Provider<GetActionsUseCase> getActionsUseCaseProvider,
      Provider<GetFoldersUseCase> getFoldersUseCaseProvider,
      Provider<CompleteActionUseCase> completeActionUseCaseProvider,
      Provider<TimeBlockRepository> timeBlockRepositoryProvider,
      Provider<EventRepository> eventRepositoryProvider) {
    return new TimeViewModel_Factory(contextProvider, getActionsUseCaseProvider, getFoldersUseCaseProvider, completeActionUseCaseProvider, timeBlockRepositoryProvider, eventRepositoryProvider);
  }

  public static TimeViewModel newInstance(Context context, GetActionsUseCase getActionsUseCase,
      GetFoldersUseCase getFoldersUseCase, CompleteActionUseCase completeActionUseCase,
      TimeBlockRepository timeBlockRepository, EventRepository eventRepository) {
    return new TimeViewModel(context, getActionsUseCase, getFoldersUseCase, completeActionUseCase, timeBlockRepository, eventRepository);
  }
}
