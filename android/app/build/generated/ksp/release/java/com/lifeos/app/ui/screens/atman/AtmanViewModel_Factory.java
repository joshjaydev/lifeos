package com.lifeos.app.ui.screens.atman;

import com.lifeos.domain.repository.ActionRepository;
import com.lifeos.domain.repository.AiSuggestionRepository;
import com.lifeos.domain.repository.ChatRepository;
import com.lifeos.domain.repository.EventRepository;
import com.lifeos.domain.repository.GoalRepository;
import com.lifeos.domain.repository.NoteRepository;
import com.lifeos.domain.repository.PrincipleRepository;
import com.lifeos.domain.repository.TimeBlockRepository;
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
public final class AtmanViewModel_Factory implements Factory<AtmanViewModel> {
  private final Provider<ChatRepository> chatRepositoryProvider;

  private final Provider<AiSuggestionRepository> aiSuggestionRepositoryProvider;

  private final Provider<ActionRepository> actionRepositoryProvider;

  private final Provider<EventRepository> eventRepositoryProvider;

  private final Provider<NoteRepository> noteRepositoryProvider;

  private final Provider<GoalRepository> goalRepositoryProvider;

  private final Provider<PrincipleRepository> principleRepositoryProvider;

  private final Provider<TimeBlockRepository> timeBlockRepositoryProvider;

  public AtmanViewModel_Factory(Provider<ChatRepository> chatRepositoryProvider,
      Provider<AiSuggestionRepository> aiSuggestionRepositoryProvider,
      Provider<ActionRepository> actionRepositoryProvider,
      Provider<EventRepository> eventRepositoryProvider,
      Provider<NoteRepository> noteRepositoryProvider,
      Provider<GoalRepository> goalRepositoryProvider,
      Provider<PrincipleRepository> principleRepositoryProvider,
      Provider<TimeBlockRepository> timeBlockRepositoryProvider) {
    this.chatRepositoryProvider = chatRepositoryProvider;
    this.aiSuggestionRepositoryProvider = aiSuggestionRepositoryProvider;
    this.actionRepositoryProvider = actionRepositoryProvider;
    this.eventRepositoryProvider = eventRepositoryProvider;
    this.noteRepositoryProvider = noteRepositoryProvider;
    this.goalRepositoryProvider = goalRepositoryProvider;
    this.principleRepositoryProvider = principleRepositoryProvider;
    this.timeBlockRepositoryProvider = timeBlockRepositoryProvider;
  }

  @Override
  public AtmanViewModel get() {
    return newInstance(chatRepositoryProvider.get(), aiSuggestionRepositoryProvider.get(), actionRepositoryProvider.get(), eventRepositoryProvider.get(), noteRepositoryProvider.get(), goalRepositoryProvider.get(), principleRepositoryProvider.get(), timeBlockRepositoryProvider.get());
  }

  public static AtmanViewModel_Factory create(Provider<ChatRepository> chatRepositoryProvider,
      Provider<AiSuggestionRepository> aiSuggestionRepositoryProvider,
      Provider<ActionRepository> actionRepositoryProvider,
      Provider<EventRepository> eventRepositoryProvider,
      Provider<NoteRepository> noteRepositoryProvider,
      Provider<GoalRepository> goalRepositoryProvider,
      Provider<PrincipleRepository> principleRepositoryProvider,
      Provider<TimeBlockRepository> timeBlockRepositoryProvider) {
    return new AtmanViewModel_Factory(chatRepositoryProvider, aiSuggestionRepositoryProvider, actionRepositoryProvider, eventRepositoryProvider, noteRepositoryProvider, goalRepositoryProvider, principleRepositoryProvider, timeBlockRepositoryProvider);
  }

  public static AtmanViewModel newInstance(ChatRepository chatRepository,
      AiSuggestionRepository aiSuggestionRepository, ActionRepository actionRepository,
      EventRepository eventRepository, NoteRepository noteRepository, GoalRepository goalRepository,
      PrincipleRepository principleRepository, TimeBlockRepository timeBlockRepository) {
    return new AtmanViewModel(chatRepository, aiSuggestionRepository, actionRepository, eventRepository, noteRepository, goalRepository, principleRepository, timeBlockRepository);
  }
}
