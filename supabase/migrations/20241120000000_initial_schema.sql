-- LifeOS Initial Schema

-- Enable UUID extension
create extension if not exists "uuid-ossp";

-- Profiles (extends Supabase auth.users)
create table profiles (
  id uuid references auth.users on delete cascade primary key,
  email text,
  display_name text,
  avatar_url text, -- for Google profile picture
  time_block_size integer default 10, -- in minutes
  created_at timestamptz default now(),
  updated_at timestamptz default now()
);

-- Folders for Actions
create table folders (
  id uuid default uuid_generate_v4() primary key,
  user_id uuid references profiles(id) on delete cascade not null,
  name text not null,
  is_default boolean default false,
  goal_id uuid, -- will reference goals table
  created_at timestamptz default now(),
  updated_at timestamptz default now()
);

-- Actions
create table actions (
  id uuid default uuid_generate_v4() primary key,
  user_id uuid references profiles(id) on delete cascade not null,
  folder_id uuid references folders(id) on delete cascade not null,
  title text not null,
  description text,
  due_date timestamptz,
  due_time time,
  is_recurring boolean default false,
  recurrence_type text check (recurrence_type in ('daily', 'weekly', 'monthly', 'yearly')),
  notify_before integer default 60, -- minutes before due
  created_at timestamptz default now(),
  updated_at timestamptz default now()
);

-- Action completions (for tracking recurring action instances)
create table action_completions (
  id uuid default uuid_generate_v4() primary key,
  action_id uuid references actions(id) on delete cascade not null,
  completed_date date not null,
  completed_at timestamptz default now()
);

-- Time blocks
create table time_blocks (
  id uuid default uuid_generate_v4() primary key,
  user_id uuid references profiles(id) on delete cascade not null,
  action_id uuid references actions(id) on delete cascade not null,
  block_date date not null,
  start_time time not null,
  end_time time not null,
  created_at timestamptz default now()
);

-- Events (Calendar)
create table events (
  id uuid default uuid_generate_v4() primary key,
  user_id uuid references profiles(id) on delete cascade not null,
  title text not null,
  description text,
  start_datetime timestamptz not null,
  end_datetime timestamptz not null,
  is_recurring boolean default false,
  recurrence_type text check (recurrence_type in ('daily', 'weekly', 'monthly', 'yearly')),
  notify_before integer default 30, -- minutes before event
  created_at timestamptz default now(),
  updated_at timestamptz default now()
);

-- Notebooks
create table notebooks (
  id uuid default uuid_generate_v4() primary key,
  user_id uuid references profiles(id) on delete cascade not null,
  name text not null,
  is_default boolean default false,
  goal_id uuid, -- will reference goals table
  created_at timestamptz default now(),
  updated_at timestamptz default now()
);

-- Notes
create table notes (
  id uuid default uuid_generate_v4() primary key,
  user_id uuid references profiles(id) on delete cascade not null,
  notebook_id uuid references notebooks(id) on delete cascade not null,
  title text not null,
  content jsonb default '{}', -- Rich text content
  is_journal boolean default false,
  journal_date date, -- for journal entries
  created_at timestamptz default now(),
  updated_at timestamptz default now()
);

-- Goals
create table goals (
  id uuid default uuid_generate_v4() primary key,
  user_id uuid references profiles(id) on delete cascade not null,
  what_do_you_want text not null,
  folder_id uuid references folders(id) on delete set null,
  notebook_id uuid references notebooks(id) on delete set null,
  created_at timestamptz default now(),
  updated_at timestamptz default now()
);

-- Add foreign key from folders and notebooks to goals
alter table folders add constraint fk_folders_goal foreign key (goal_id) references goals(id) on delete set null;
alter table notebooks add constraint fk_notebooks_goal foreign key (goal_id) references goals(id) on delete set null;

-- Principles
create table principles (
  id uuid default uuid_generate_v4() primary key,
  user_id uuid references profiles(id) on delete cascade not null,
  parent_id uuid references principles(id) on delete cascade, -- for sub-principles
  fundamental_truth text not null,
  experience text, -- optional experience/failure
  created_at timestamptz default now(),
  updated_at timestamptz default now()
);

-- Row Level Security Policies
alter table profiles enable row level security;
alter table folders enable row level security;
alter table actions enable row level security;
alter table action_completions enable row level security;
alter table time_blocks enable row level security;
alter table events enable row level security;
alter table notebooks enable row level security;
alter table notes enable row level security;
alter table goals enable row level security;
alter table principles enable row level security;

-- Profiles policies
create policy "Users can view own profile" on profiles for select using (auth.uid() = id);
create policy "Users can update own profile" on profiles for update using (auth.uid() = id);
create policy "Users can insert own profile" on profiles for insert with check (auth.uid() = id);

-- Folders policies
create policy "Users can view own folders" on folders for select using (auth.uid() = user_id);
create policy "Users can create own folders" on folders for insert with check (auth.uid() = user_id);
create policy "Users can update own folders" on folders for update using (auth.uid() = user_id);
create policy "Users can delete own folders" on folders for delete using (auth.uid() = user_id);

-- Actions policies
create policy "Users can view own actions" on actions for select using (auth.uid() = user_id);
create policy "Users can create own actions" on actions for insert with check (auth.uid() = user_id);
create policy "Users can update own actions" on actions for update using (auth.uid() = user_id);
create policy "Users can delete own actions" on actions for delete using (auth.uid() = user_id);

-- Action completions policies
create policy "Users can view own completions" on action_completions for select
  using (exists (select 1 from actions where actions.id = action_completions.action_id and actions.user_id = auth.uid()));
create policy "Users can create own completions" on action_completions for insert
  with check (exists (select 1 from actions where actions.id = action_completions.action_id and actions.user_id = auth.uid()));
create policy "Users can delete own completions" on action_completions for delete
  using (exists (select 1 from actions where actions.id = action_completions.action_id and actions.user_id = auth.uid()));

-- Time blocks policies
create policy "Users can view own time blocks" on time_blocks for select using (auth.uid() = user_id);
create policy "Users can create own time blocks" on time_blocks for insert with check (auth.uid() = user_id);
create policy "Users can update own time blocks" on time_blocks for update using (auth.uid() = user_id);
create policy "Users can delete own time blocks" on time_blocks for delete using (auth.uid() = user_id);

-- Events policies
create policy "Users can view own events" on events for select using (auth.uid() = user_id);
create policy "Users can create own events" on events for insert with check (auth.uid() = user_id);
create policy "Users can update own events" on events for update using (auth.uid() = user_id);
create policy "Users can delete own events" on events for delete using (auth.uid() = user_id);

-- Notebooks policies
create policy "Users can view own notebooks" on notebooks for select using (auth.uid() = user_id);
create policy "Users can create own notebooks" on notebooks for insert with check (auth.uid() = user_id);
create policy "Users can update own notebooks" on notebooks for update using (auth.uid() = user_id);
create policy "Users can delete own notebooks" on notebooks for delete using (auth.uid() = user_id);

-- Notes policies
create policy "Users can view own notes" on notes for select using (auth.uid() = user_id);
create policy "Users can create own notes" on notes for insert with check (auth.uid() = user_id);
create policy "Users can update own notes" on notes for update using (auth.uid() = user_id);
create policy "Users can delete own notes" on notes for delete using (auth.uid() = user_id);

-- Goals policies
create policy "Users can view own goals" on goals for select using (auth.uid() = user_id);
create policy "Users can create own goals" on goals for insert with check (auth.uid() = user_id);
create policy "Users can update own goals" on goals for update using (auth.uid() = user_id);
create policy "Users can delete own goals" on goals for delete using (auth.uid() = user_id);

-- Principles policies
create policy "Users can view own principles" on principles for select using (auth.uid() = user_id);
create policy "Users can create own principles" on principles for insert with check (auth.uid() = user_id);
create policy "Users can update own principles" on principles for update using (auth.uid() = user_id);
create policy "Users can delete own principles" on principles for delete using (auth.uid() = user_id);

-- Function to create profile and defaults on user signup
create or replace function handle_new_user()
returns trigger as $$
declare
  default_folder_id uuid;
  default_notebook_id uuid;
begin
  -- Create profile (supports both email/password and OAuth)
  insert into profiles (id, email, display_name, avatar_url)
  values (
    new.id,
    new.email,
    coalesce(new.raw_user_meta_data->>'full_name', new.raw_user_meta_data->>'name'),
    new.raw_user_meta_data->>'avatar_url'
  );

  -- Create default folder
  insert into folders (user_id, name, is_default)
  values (new.id, 'General Actions', true)
  returning id into default_folder_id;

  -- Create default notebook
  insert into notebooks (user_id, name, is_default)
  values (new.id, 'General', true)
  returning id into default_notebook_id;

  -- Create Journal note in default notebook
  insert into notes (user_id, notebook_id, title, is_journal)
  values (new.id, default_notebook_id, 'Journal', true);

  return new;
end;
$$ language plpgsql security definer;

-- Trigger for new user signup
create trigger on_auth_user_created
  after insert on auth.users
  for each row execute function handle_new_user();

-- Function to handle goal creation (creates folder and notebook)
create or replace function handle_goal_creation()
returns trigger as $$
declare
  new_folder_id uuid;
  new_notebook_id uuid;
begin
  -- Create folder for goal
  insert into folders (user_id, name, goal_id)
  values (new.user_id, new.what_do_you_want, new.id)
  returning id into new_folder_id;

  -- Create notebook for goal
  insert into notebooks (user_id, name, goal_id)
  values (new.user_id, new.what_do_you_want, new.id)
  returning id into new_notebook_id;

  -- Update goal with folder and notebook ids
  update goals
  set folder_id = new_folder_id, notebook_id = new_notebook_id
  where id = new.id;

  return new;
end;
$$ language plpgsql security definer;

-- Trigger for goal creation
create trigger on_goal_created
  after insert on goals
  for each row execute function handle_goal_creation();

-- Function to handle goal deletion (cascades to folder and notebook)
create or replace function handle_goal_deletion()
returns trigger as $$
begin
  -- Delete associated folder
  delete from folders where goal_id = old.id;

  -- Delete associated notebook
  delete from notebooks where goal_id = old.id;

  return old;
end;
$$ language plpgsql security definer;

-- Trigger for goal deletion
create trigger on_goal_deleted
  before delete on goals
  for each row execute function handle_goal_deletion();

-- Indexes for performance
create index idx_actions_user_id on actions(user_id);
create index idx_actions_folder_id on actions(folder_id);
create index idx_actions_due_date on actions(due_date);
create index idx_events_user_id on events(user_id);
create index idx_events_start_datetime on events(start_datetime);
create index idx_notes_notebook_id on notes(notebook_id);
create index idx_notes_journal_date on notes(journal_date);
create index idx_time_blocks_user_id on time_blocks(user_id);
create index idx_time_blocks_block_date on time_blocks(block_date);
create index idx_principles_parent_id on principles(parent_id);
