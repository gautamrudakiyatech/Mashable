# **Web Scraping App - Mashable News Tracker**

This Android app uses **web scraping** to fetch the latest news from the website [Mashable India](https://in.mashable.com/). It features a **clean, modern UI**, leveraging **Fragments** for better navigation, **Picasso** for image loading, **JSoup** for scraping, **Shimmer** for loading animations, and various **animations** to enhance the user experience.

---

## **Table of Contents**

- [Description](#description)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

---

## **Description**

This app allows users to read the latest articles from **Mashable India** by scraping data from the website using **JSoup**. The app provides a **smooth and engaging user experience** with **image loading** using **Picasso**, **shimmer effects** for loading indicators, and **animations** for a dynamic UI. The app is optimized for performance and includes **Fragments** for managing the app’s sections efficiently.

---

## **Features**

- **Web Scraping**: Scrape the latest news articles from [Mashable India](https://in.mashable.com/) using **JSoup**.
- **Good UI**: Clean, modern UI design with **shimmer effects** for smooth loading experience.
- **Fragments**: Use of Fragments for easy navigation between sections (e.g., News Feed, Categories, Settings).
- **Picasso Integration**: Load images efficiently using the **Picasso** library.
- **Shimmer Effect**: Implemented **Shimmer** to show loading animations while data is being fetched.
- **UI Animations**: Add engaging animations for seamless user experience.
- **Offline Storage**: Cache articles and images for offline reading.
- **Responsive Design**: Optimized for different screen sizes and orientations.

---

## **Technologies Used**

- **Android SDK**: Java, Android Studio
- **Libraries**:
  - **Picasso** for image loading.
  - **JSoup** for web scraping.
  - **Shimmer** for loading animations.
  - **Fragments** for UI navigation.
  - **Android Animation** APIs for smooth transitions.
- **API Integration**: No external APIs used, only direct scraping from the website.
- **Storage**: Local storage for caching images and articles.

---

## **Installation**

Follow the steps below to set up and run the app:

1. **Clone the repository**:
   ```bash
   git clone https://github.com/username/mashable.git
   ```

2. **Open the project in Android Studio**.

3. **Install dependencies**: Android Studio will automatically sync and install the required dependencies.

4. **Run the app**: Connect an Android device or emulator and click "Run" in Android Studio.

5. **Setup**: Make sure to check the **internet permissions** in the `AndroidManifest.xml` for web scraping:
   ```xml
   <uses-permission android:name="android.permission.INTERNET" />
   ```

---

## **Usage**

1. **Launch the app** on your Android device or emulator.
2. The main screen displays a **list of the latest news articles** fetched from Mashable India.
3. Tap on any article to open a **detailed view**.
4. Use the **menu** or **tabs** (implemented with Fragments) to explore different categories of news.
5. The **shimmer effect** will appear while the app loads articles and images.
6. If there is no internet, the app will show cached articles stored locally.

---


### **Example README File Structure:**

```markdown
# Web Scraping App - Mashable News Tracker

An Android app to fetch the latest news from Mashable India through web scraping.

## Table of Contents

- Descriptio
- Features
- Technologies Used
- Installation
- Usage

## Description

This Android app scrapes the latest articles from Mashable India using JSoup. It features a modern UI with Picasso for image loading, Shimmer for smooth loading, and animations to enhance the user experience. The app uses Fragments for efficient navigation and offline caching for articles.

## Features

- Web scraping from Mashable India using JSoup.
- Clean UI with Picasso for image loading.
- Shimmer effects for smooth loading.
- Fragments for app navigation.
- Offline caching for articles.

## Technologies Used

- **Android SDK**: Java, Android Studio.
- **Libraries**: Picasso, JSoup, Shimmer.
- **APIs**: No external APIs, uses direct scraping.
- **Storage**: Caching images and articles locally.

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/username/mashable.git
   ```
2. Open in Android Studio.
3. Install dependencies and run the app on your Android device or emulator.

## Usage

1. Launch the app and view the latest news articles.
2. Use the menu or tabs to explore different categories.
3. Enjoy smooth loading animations with Shimmer and image loading with Picasso.

---
