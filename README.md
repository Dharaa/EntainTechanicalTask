# EntainTechanicalTask

<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#key-points">Key Points</a></li>
    <li><a href="#time-spent">Time spent</a></li>
    <li><a href="#contact">Contact</a></li>
  </ol>
</details>

<!-- ABOUT THE PROJECT -->
## About The Project

Android app that displays "Next to Go" races using Entain web API.

### Built With

- Minimum SDK level 24
- [Kotlin](https://developer.android.com/kotlin?hl=en) based, [Coroutines](https://developer.android.com/kotlin/coroutines) + [Flow](https://developer.android.com/kotlin/flow) for asynchronous.
- [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) - Hilt Dependency injection library for Android that reduces the boilerplate.
- [Jetpack](https://developer.android.com/jetpack/compose)
  - Implemented LazyColumn.
  - ViewModel - Manages UI-related data holders and lifecycle aware. Allows data to survive configuration changes such as screen rotations.
- [Architecture](https://developer.android.com/topic/architecture) - Android Clean Architecture (Data - Domain - Presentation)
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit) - Construct the REST APIs.
- [Moshi](https://github.com/square/moshi/) - A modern JSON library for Kotlin and Java.
- [Room](https://developer.android.com/training/data-storage/room) - The Room persistence library for local database.

Getting Started
---------------
>Note: To compile, build, and run this app, I used Android Studio Giraffe | 2022.3.1 Patch 2 with Java 17.
1. [Install Android Studio](https://developer.android.com/studio/install.html), if you don't already
   have it.
2. Clone the repo
   ```sh
   git clone https://github.com/Dharra/EntainTechanicalTask.git
   ```
3. Import the project into Android Studio.
4. Build and run the sample.

### Installation
- Supports any device with a minimum SDK of 24.
- Tested and developed using Pixel 6 API 33 emulator.

<!-- ROADMAP -->
## Roadmap

- [x] As a user, I should be able to see a time ordered list of races ordered by advertised start ascending
- [x] As a user, I should not see races that are one minute past the advertised start 
- [x] As a user, I should be able to filter my list of races by the following categories: Horse, Harness & Greyhound racing
- [x] As a user, I can deselect all filters to show the next 5 from of all racing categories
- [x] As a user I should see the meeting name, race number and advertised start as a countdown for each race.
- [x] As a user, I should always see 5 races and data should automatically refresh

## Key Points
- I focused on the architecture part and implemented Android Clean Architecture. This approach significantly enhances the app's maintainability, quality, and robustness. It also ensures the app's scalability, making it well-prepared for future growth.
- I employed use cases within the domain layer, which helped prevent code duplication and enhanced the readability of the classes.
- I implemented an interface structure to ensure that the repository interface (referred to as the Gateway in Clean Architecture) acts as a means to invert dependencies. This way, the domain layer remains independent of the outer layers, including repositories, ensuring better separation of concerns.
- I stored the categories in a local database since the category IDs were fixed. This approach simplifies the filtering process, making it more efficient and straightforward.

### More Fact
- I believe I tried to cover most of the important aspects through this small project. I would like to express my interest in working with the Entain team because I believe your team is composed of innovative developers. I always maintain a learning attitude and love to work on new challenges every day. I am looking forward to learning more about the Entain team and gaining insights into the architecture and tech stacks that the team uses.

## Time Spent  
It took me approximately 10-12 hours to complete the project. I decided to split the work over 2 days to ensure a fresh perspective. I thoroughly enjoyed working on this project, as it allowed me to explore new approaches and techniques. The experience was both fun and rewarding, and I was pleased with the final outcome.

<!-- CONTACT -->
## Contact

Dhara Kavathiya - [Linkedin](linkedin.com/in/dhara-kavathiya-30b97bbb) - dharap404@gmail.com

Project Link: [https://github.com/Dharaa/EntainTechanicalTask](https://github.com/Dharaa/EntainTechanicalTask)

<p align="right">(<a href="#readme-top">back to top</a>)</p>
