package com.lifeos.app.ui.screens.time;

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
@QualifierMetadata
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
  private final Provider<GetActionsUseCase> getActionsUseCaseProvider;

  private final Provider<GetFoldersUseCase> getFoldersUseCaseProvider;

  private final Provider<CompleteActionUseCase> completeActionUseCaseProvider;

  private final Provider<TimeBlockRepository> timeBlockRepositoryProvider;

  private final Provider<EventRepository> eventRepositoryProvider;

  public TimeViewModel_Factory(Provider<GetActionsUseCase> getActionsUseCaseProvider,
      Provider<GetFoldersUseCase> getFoldersUseCaseProvider,
      Provider<CompleteActionUseCase> completeActionUseCaseProvider,
      Provider<TimeBlockRepository> timeBlockRepositoryProvider,
      Provider<EventRepository> eventRepositoryProvider) {
    this.getActionsUseCaseProvider = getActionsUseCaseProvider;
    this.getFoldersUseCaseProvider = getFoldersUseCaseProvider;
    this.completeActionUseCaseProvider = completeActionUseCaseProvider;
    this.timeBlockRepositoryProvider = timeBlockRepositoryProvider;
    this.eventRepositoryProvider = eventRepositoryProvider;
  }

  @Override
  public TimeViewModel get() {
    return newInstance(getActionsUseCaseProvider.get(), getFoldersUseCaseProvider.get(), completeActionUseCaseProvider.get(), timeBlockRepositoryProvider.get(), eventRepositoryProvider.get());
  }

  public static TimeViewModel_Factory create(Provider<GetActionsUseCase> getActionsUseCaseProvider,
      Provider<GetFoldersUseCase> getFoldersUseCaseProvider,
      Provider<CompleteActionUseCase> completeActionUseCaseProvider,
      Provider<TimeBlockRepository> timeBlockRepositoryProvider,
      Provider<EventRepository> eventRepositoryProvider) {
    return new TimeViewModel_Factory(getActionsUseCaseProvider, getFoldersUseCaseProvider, completeActionUseCaseProvider, timeBlockRepositoryProvider, eventRepositoryProvider);
  }

  public static TimeViewModel newInstance(GetActionsUseCase getActionsUseCase,
      GetFoldersUseCase getFoldersUseCase, CompleteActionUseCase completeActionUseCase,
      TimeBlockRepository timeBlockRepository, EventRepository eventRepository) {
    return new TimeViewModel(getActionsUseCase, getFoldersUseCase, completeActionUseCase, timeBlockRepository, eventRepository);
  }
}
