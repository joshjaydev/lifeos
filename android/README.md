# LifeOS Android App

Android app for LifeOS - a personal life operating system with notes, actions, time blocking, and AI assistance.

## Setup

1. Copy `local.properties.example` to `local.properties`
2. Fill in your Supabase and API credentials
3. Open the project in Android Studio
4. Sync Gradle and run

## Configuration Required

- **SUPABASE_URL**: Your Supabase project URL
- **SUPABASE_ANON_KEY**: Your Supabase anonymous key
- **GEMINI_API_KEY**: Google Gemini API key for Atman AI features
- **GOOGLE_WEB_CLIENT_ID**: Google OAuth web client ID for authentication

## Architecture

- **UI**: Jetpack Compose with Material 3
- **DI**: Hilt
- **Database**: Room (offline-first)
- **Network**: Supabase Kotlin client
- **State**: ViewModel + Flow

## Features

- Notes & Notebooks
- Actions & Folders
- Time Blocking (Planner/Block/Calendar)
- Atman AI Assistant (Gemini)
- Goals & Principles
