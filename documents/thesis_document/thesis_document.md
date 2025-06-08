# Inclusive Trip Planner

**Moonshot Project – ALGOSUP**  
**Author**: David Cuahonte Cuevas 
**Submission Date**: June 10, 2025



# Summary

This thesis presents the development of **Inclusive Trip Planner**, a mobile application designed to assist individuals with disabilities in planning accessible trips. The app enables users to create profiles, indicate their accessibility needs, browse and save inclusive itineraries, and review destinations based on criteria such as wheelchair access or visual signage.

The system was developed as a native Android application using **Jetpack Compose** [1](#1-jetpack-compose) and **Kotlin**. It is supported by a backend built with **Spring Boot** [2](#2-spring-boot) and **PostgreSQL** [3](#3-postgresql), structured following a multi-layered architecture that separates controllers, services, repositories, and **DTOs** [4](#4-dto-data-transfer-object).


A waterfall-inspired methodology guided the project, with phases dedicated to specifications, interface prototyping, backend implementation, and integration. Testing was conducted at multiple levels, including REST API validation and feature-specific integration tests on the frontend. The resulting prototype demonstrates the feasibility of inclusive itinerary recommendations, reinforcing the importance of **accessibility** [5] in digital travel platforms.

**Keywords:** Accessibility, Inclusive Travel, Trip Planning, User Experience, Mobility, Digital Inclusion.



# Table of Contents


- [Inclusive Trip Planner](#inclusive-trip-planner)
- [Summary](#summary)
- [Table of Contents](#table-of-contents)
- [1. Introduction](#1-introduction)
  - [2. Motivation](#2-motivation)
    - [2.1. Relevance of Mobile Travel Apps for Accessibility](#21-relevance-of-mobile-travel-apps-for-accessibility)
    - [2.2. Gaps in Accessibility-Aware Travel Planning](#22-gaps-in-accessibility-aware-travel-planning)
    - [2.3. Project Impact and Contribution](#23-project-impact-and-contribution)
- [3. Objectives](#3-objectives)
  - [3.1 General Objective](#31-general-objective)
  - [3.2 Specific Objectives](#32-specific-objectives)
- [4. State of the Art](#4-state-of-the-art)
  - [4.1 Accessibility in Digital Travel Tools](#41-accessibility-in-digital-travel-tools)
  - [4.2 Gaps in Mainstream Travel Planning Applications](#42-gaps-in-mainstream-travel-planning-applications)
  - [4.3 Inclusive Filtering and Personalized Itinerary Generation](#43-inclusive-filtering-and-personalized-itinerary-generation)
  - [4.4 Conclusions of the State of the Art](#44-conclusions-of-the-state-of-the-art)
- [5. Hypothesis](#5-hypothesis)
  - [5.1 Main Hypothesis](#51-main-hypothesis)
  - [5.2 Supporting Assumptions](#52-supporting-assumptions)
    - [Usability improvement through personalized filtering](#usability-improvement-through-personalized-filtering)
    - [Feasibility of itinerary construction based on real data](#feasibility-of-itinerary-construction-based-on-real-data)
- [6. Thesis](#6-thesis)
  - [6.1 Central Thesis Statement](#61-central-thesis-statement)
  - [6.2 Justification of the Thesis](#62-justification-of-the-thesis)
- [7. Project Management](#7-project-management)
  - [7.1 Milestones](#71-milestones)
  - [7.2 Tasks](#72-tasks)
    - [1. Documentation and Planning](#1-documentation-and-planning)
    - [2. Frontend Development](#2-frontend-development)
    - [3. Backend Development](#3-backend-development)
    - [4. Frontend-Backend Integration](#4-frontend-backend-integration)
    - [5. Authentication Implementation](#5-authentication-implementation)
    - [6. Testing and Finalization](#6-testing-and-finalization)
    - [7. Documentation and Presentation](#7-documentation-and-presentation)
  - [7.3 Gantt Chart](#73-gantt-chart)
- [8. Methodology](#8-methodology)
  - [8.1 Application Design](#81-application-design)
  - [8.2 Tools and Technologies](#82-tools-and-technologies)
  - [8.3 System Architecture](#83-system-architecture)
- [9 Development](#9-development)
  - [9.1 Functional Architecture and Itinerary Design](#91-functional-architecture-and-itinerary-design)
  - [9.2 Itinerary Flow and Feature Implementation](#92-itinerary-flow-and-feature-implementation)
  - [9.3 Backend Architecture and Security](#93-backend-architecture-and-security)
    - [Entity Structure](#entity-structure)
      - [User Entity](#user-entity)
      - [Setting Entity](#setting-entity)
    - [Integration Testing Approach](#integration-testing-approach)
      - [Example: `UserServiceTest`](#example-userservicetest)
      - [Example: `SettingServiceTest`](#example-settingservicetest)
    - [JWT-Based Security](#jwt-based-security)
      - [Token Generation: `JwtService`](#token-generation-jwtservice)
      - [Token Validation: `JwtAuthenticationFilter`](#token-validation-jwtauthenticationfilter)
      - [Password Logic and Platform Rules](#password-logic-and-platform-rules)
    - [Spring Security Configuration](#spring-security-configuration)
  - [9.4 Authentication and Identity Integration](#94-authentication-and-identity-integration)
    - [Email and Password Login](#email-and-password-login)
    - [Firebase-Based Social Login Flow (Google \& Facebook)](#firebase-based-social-login-flow-google--facebook)
      - [Login Flow Overview](#login-flow-overview)
    - [Security and Validation Rules](#security-and-validation-rules)
      - [1. Password Requirements for Email-Based Users](#1-password-requirements-for-email-based-users)
      - [2. Rules for Social Login Users (Google or Facebook)](#2-rules-for-social-login-users-google-or-facebook)
      - [3. Centralized Validation](#3-centralized-validation)
      - [4. Access Control via JWT](#4-access-control-via-jwt)
    - [Platform Rules Comparison Table](#platform-rules-comparison-table)
  - [9.5 Frontend Architecture](#95-frontend-architecture)
    - [Architecture Overview](#architecture-overview)
    - [ViewModel and State Handling](#viewmodel-and-state-handling)
      - [Purpose and Structure](#purpose-and-structure)
      - [State Management with StateFlow](#state-management-with-stateflow)
      - [Lifecycle Awareness](#lifecycle-awareness)
      - [Reactive UI Consumption](#reactive-ui-consumption)
      - [Specialized ViewModels](#specialized-viewmodels)
      - [Error and Result Handling](#error-and-result-handling)
    - [Retrofit and API Integration](#retrofit-and-api-integration)
      - [RetrofitClient Initialization](#retrofitclient-initialization)
      - [Authorization with Interceptor](#authorization-with-interceptor)
      - [Request and Response Models](#request-and-response-models)
      - [No Dynamic Base URL or Environment Switching](#no-dynamic-base-url-or-environment-switching)
      - [Localized Error Handling](#localized-error-handling)
    - [Data Transfer Objects (DTOs)](#data-transfer-objects-dtos)
      - [Purpose and Design](#purpose-and-design)
      - [Examples of DTOs](#examples-of-dtos)
      - [DTO Simplicity and Usage](#dto-simplicity-and-usage)
      - [Flow of Data in the App](#flow-of-data-in-the-app)
      - [Future Considerations](#future-considerations)
    - [Navigation and Screen Structure](#navigation-and-screen-structure)
      - [Navigation Setup in MainActivity](#navigation-setup-in-mainactivity)
      - [Parameterized Routes and Navigation Arguments](#parameterized-routes-and-navigation-arguments)
      - [ViewModel Lifecycle and Sharing](#viewmodel-lifecycle-and-sharing)
      - [Post-Login Routing and Manual Control](#post-login-routing-and-manual-control)
      - [Future Considerations](#future-considerations-1)
- [10 Demonstration of the project](#10-demonstration-of-the-project)
    - [10.1 Setup and Execution Environment](#101-setup-and-execution-environment)
      - [Frontend Environment](#frontend-environment)
      - [Backend Environment](#backend-environment)
    - [10.2 Demonstration Objectives](#102-demonstration-objectives)
    - [10.3 Execution Flow and Screens](#103-execution-flow-and-screens)
      - [App Launch and Authentication](#app-launch-and-authentication)
        - [Login via Email or Phone](#login-via-email-or-phone)
    - [10.3.2 Signup Flow](#1032-signup-flow)
      - [SignUpNameScreen](#signupnamescreen)
      - [SignUpEmailScreen / SignUpPhoneScreen](#signupemailscreen--signupphonescreen)
      - [SignUpPasswordScreen](#signuppasswordscreen)
      - [SignUpAccessibilityScreen](#signupaccessibilityscreen)
      - [SignUpCountriesScreen](#signupcountriesscreen)
      - [Backend Integration](#backend-integration)
    - [10.3.3 Home Screen and Itinerary Access](#1033-home-screen-and-itinerary-access)
      - [Next Plan Section](#next-plan-section)
      - [Available Itineraries List](#available-itineraries-list)
      - [Technical Notes](#technical-notes)
      - [Optional States and Fallbacks](#optional-states-and-fallbacks)
    - [10.3.4 Itinerary Details](#1034-itinerary-details)
    - [10.3.5 Itinerary Steps](#1035-itinerary-steps)
      - [Structure and Navigation](#structure-and-navigation)
      - [Data Handling and Integration](#data-handling-and-integration)
      - [Implementation Notes](#implementation-notes)
    - [10.3.6 Review Submission](#1036-review-submission)
      - [Access Points](#access-points)
      - [WriteAReviewScreen](#writeareviewscreen)
      - [Backend Integration](#backend-integration-1)
      - [Review Display](#review-display)
      - [Technical Notes](#technical-notes-1)
    - [10.3.7 Saved Itineraries](#1037-saved-itineraries)
      - [Saving and Removing Itineraries](#saving-and-removing-itineraries)
      - [SavedItinerariesScreen](#saveditinerariesscreen)
      - [Backend Integration](#backend-integration-2)
      - [Fallback and Error Handling](#fallback-and-error-handling)
    - [10.3.9 Profile Screen](#1039-profile-screen)
      - [Layout and Structure](#layout-and-structure)
      - [Backend Integration](#backend-integration-3)
    - [10.4 Visual Feedback and Responsiveness](#104-visual-feedback-and-responsiveness)
      - [Loading Indicators and Progress Feedback](#loading-indicators-and-progress-feedback)
      - [Error Messaging and Fallback States](#error-messaging-and-fallback-states)
      - [Dynamic UI Behavior](#dynamic-ui-behavior)
      - [UI Layout and Spacing Improvements](#ui-layout-and-spacing-improvements)
      - [Accessibility Considerations](#accessibility-considerations)
      - [Conclusion](#conclusion)
    - [10.5.1 Database State Verification](#1051-database-state-verification)
      - [Saved Itinerary Entry](#saved-itinerary-entry)
      - [Submitted Review Entry](#submitted-review-entry)
    - [10.5.2 API Activity and Logging](#1052-api-activity-and-logging)
      - [Android Logcat Monitoring](#android-logcat-monitoring)
      - [Spring Boot Console Output](#spring-boot-console-output)
      - [JWT Validation and Error Feedback](#jwt-validation-and-error-feedback)
    - [10.5.3 Error Feedback Confirmation](#1053-error-feedback-confirmation)
      - [Invalid Token Handling](#invalid-token-handling)
      - [Duplicate Save Request](#duplicate-save-request)
      - [Missing Fields in Review Submission](#missing-fields-in-review-submission)
    - [10.5.4 Summary of API Observability](#1054-summary-of-api-observability)
      - [Observable Layers](#observable-layers)
    - [10.6 Summary and Next Steps](#106-summary-and-next-steps)
- [Conclusions](#conclusions)
  - [11.1 Summary of Results](#111-summary-of-results)
    - [11.2 Implications of the Results](#112-implications-of-the-results)
    - [11.3 Limitations](#113-limitations)
      - [Static Itinerary Content](#static-itinerary-content)
      - [Limited Accessibility Logic](#limited-accessibility-logic)
      - [Authentication Flow Gaps](#authentication-flow-gaps)
      - [No Offline or Cache Mode](#no-offline-or-cache-mode)
      - [MVP Constraints](#mvp-constraints)
    - [11.4 Future Work](#114-future-work)
      - [1. Personalized Itinerary Recommendations](#1-personalized-itinerary-recommendations)
      - [2. OTP Verification and Full Phone Signup Support](#2-otp-verification-and-full-phone-signup-support)
      - [3. Offline Access and Smart Caching](#3-offline-access-and-smart-caching)
      - [4. Accessibility-Specific Enhancements](#4-accessibility-specific-enhancements)
      - [5. Admin Interface and Content Expansion](#5-admin-interface-and-content-expansion)
      - [6. Deployment and User Testing](#6-deployment-and-user-testing)
    - [11.5 Final Words](#115-final-words)
  - [Appendix](#appendix)
    - [\[1\] Jetpack Compose](#1-jetpack-compose)
    - [\[2\] Spring Boot](#2-spring-boot)
    - [\[3\] PostgreSQL](#3-postgresql)
    - [\[4\] DTO (Data Transfer Object)](#4-dto-data-transfer-object)
    - [\[5\] Accessibility](#5-accessibility)
    - [\[6\] WCAG (Web Content Accessibility Guidelines)](#6-wcag-web-content-accessibility-guidelines)
    - [\[7\] Firebase Authentication](#7-firebase-authentication)
    - [\[8\] Liquibase](#8-liquibase)
    - [\[9\] Retrofit](#9-retrofit)
    - [\[10\] ViewModel](#10-viewmodel)
    - [\[11\] StateFlow](#11-stateflow)
    - [\[12\] TokenManager](#12-tokenmanager)
    - [\[13\] AuthInterceptor](#13-authinterceptor)

# 1. Introduction

In recent years, accessibility has become a central concern in the development of digital services. While substantial progress has been made in areas such as web content compliance, inclusive user interfaces, and adaptive hardware, there remains a critical gap in how accessibility is considered in **travel planning applications**. The act of organizing a trip, choosing destinations, and navigating transportation is inherently complex for users with disabilities or specific mobility, sensory, or cognitive needs. Despite the availability of popular trip planning tools, these platforms often neglect to provide meaningful, actionable accessibility information.

This lack not only limits the autonomy of these users but also contributes to travel anxiety, exclusion from certain public or cultural spaces, and an overreliance on third-party information or companions. For example, a user in a wheelchair may find it difficult to know in advance whether a particular museum is fully accessible, or a visually impaired traveler may struggle to identify safe, clearly marked walking routes. In most current applications, accessibility is reduced to a checkbox or vague label, with little context or user-driven validation.

The **Inclusive Trip Planner** project addresses these limitations directly. Its objective is to offer an inclusive, mobile-first travel planning solution that places accessibility at the core of the user experience. The application allows users to create personalized profiles that reflect their accessibility preferences and constraints, and based on that information, they can explore, review, and save itineraries filtered by real accessibility criteria — such as the presence of elevators, braille signage, accessible transportation routes, or ramps.

Technically, the system consists of a native Android application built with Jetpack Compose and Kotlin, and a backend API developed using Java Spring Boot and PostgreSQL. The frontend communicates with the backend via RESTful services, ensuring real-time data flow and responsive user interaction.

Development followed a waterfall-inspired approach. After initial specification and research, the frontend mockup was created and validated before implementing the backend logic and database. Once both components were functional, a full integration phase ensured the application operated as a cohesive unit. Testing was conducted at both backend and frontend levels, including integration testing and feature validation.

This document details the full process, from motivation and analysis to technical implementation and testing. It also reflects on the management of the project, the rationale behind the software architecture, and the future potential for growth beyond the current MVP. Ultimately, this project aims to contribute to the broader field of digital inclusion by demonstrating the feasibility of accessibility-aware trip planning systems.


## 2. Motivation

Accessibility is a growing concern in the development of digital services. While many platforms have improved in areas like content and interface accessibility, complex activities such as travel planning often remain inaccessible to users with disabilities.

This gap affects a wide range of users who face barriers when organizing trips, selecting destinations, or navigating transportation systems. Most current apps lack meaningful accessibility filters or personalized support, leading to uncertainty, frustration, or exclusion.

This project was motivated by the need to address that gap through a solution designed specifically for inclusive trip planning. The idea emerged from a personal experience during a trip in my home country, where organizing visits with elderly relatives proved difficult due to the lack of reliable information about accessibility in many public and cultural spaces. This showcased how existing tools often fall short for users with mobility challenges, reinforcing the need for a platform where accessibility is not an afterthought, but a central design principle in the travel planning experience.

### 2.1. Relevance of Mobile Travel Apps for Accessibility

Mobile travel applications have become essential tools for organizing trips, discovering destinations, and navigating transportation. Their popularity has grown steadily, especially among users who rely on smartphones for daily planning and real-time guidance during their travels.

Despite this, most mainstream travel apps focus on general convenience rather than accessibility. They rarely include features that help users with disabilities evaluate whether a destination is suitable for their needs. This often excludes key details like step-free access, ramps, elevators, braille signage, or assistive technology support.

The decision to focus on mobile trip planning in this thesis is based on both the **high adoption** of travel apps and the **lack of inclusive features** within them.

### 2.2. Gaps in Accessibility-Aware Travel Planning

While some platforms have started to include minimal accessibility labels, they often present generic or incomplete information. Users with specific mobility or sensory needs are left without clear indicators of whether a destination or route is suitable for them.

This lack of detail leads to uncertainty and discourages independent travel planning. For example, a destination might be marked “accessible” without specifying whether that includes elevator access, step-free paths, braille signage, or adapted restrooms. These oversights force users to rely on external sources or personal trial and error.

The Inclusive Trip Planner aims to solve this issue by combining accessibility data, user reviews, and itinerary customization into a single mobile platform. This allows users to plan trips with greater confidence and clarity, ensuring that travel decisions are made based on actual access needs.

### 2.3. Project Impact and Contribution

This project explores the development of a mobile travel planning application that integrates accessibility into its core structure. By allowing users to browse and save inclusive itineraries based on specific needs, it addresses a common limitation found in many existing travel platforms: the absence of actionable accessibility information.

While the current version focuses on a curated set of steps and destinations, it demonstrates the potential of structuring trip planning around real user constraints and access requirements. The project highlights how even a limited, targeted implementation focused on specific itineraries can improve usability for underserved groups and lay the groundwork for more inclusive tools.

This work may also serve as a basis for future improvements or research related to inclusive travel design, user-centered accessibility features, and personalized itinerary generation.

# 3. Objectives

This section presents the objectives that guided the development of this thesis. It begins with a general objective, followed by specific goals that frame the project’s technical direction, development milestones, and validation steps.

## 3.1 General Objective

To design, develop, and validate a mobile application prototype that enables inclusive trip planning, focused on accessibility-related user needs and constraints.

## 3.2 Specific Objectives

1. **Review existing tools and research related to accessible trip planning.**

   - Analyze the capabilities and limitations of current mainstream travel apps (Such as booking or GetYourGuide).
   - Identify gaps in accessibility features and data availability in these systems.

2. **Define and structure a functional and technical specification adapted to accessibility needs.**

   - Establish core app functionalities around user profiling, itinerary saving, and accessibility tagging.
   - Define the backend architecture, data models, and communication structure.

3. **Develop a working MVP of the mobile application.**

   - Implement the initial frontend components using Jetpack Compose and Kotlin, ensuring they align with the core accessibility logic.
   - Implement the backend API using Spring Boot and PostgreSQL.

4. **Integrate the frontend and backend into a unified system.**

   - Connect UI components to live backend data.
   - Enable user interaction with itineraries and accessibility data.

5. **Test the core features of the system.**

   - Conduct integration testing between modules.
   - Validate expected user flows: onboarding, saving itineraries, and submitting reviews.

6. **Evaluate system limitations and define directions for future feature expansion.**

   - Reflect on incomplete features or technical constraints encountered.
   - Propose realistic extensions for future development stages.

7. **Document the development process and technical decisions.**
   - Maintain clear records of architecture, testing logic, and model structure.
   - Align documentation with thesis evaluation requirements.

# 4. State of the Art

This section presents a review of existing tools, research, and approaches related to accessibility in travel planning applications and inclusive mobile design. The objective is to provide a comprehensive overview of the current technological and user experience landscape, emphasizing the gaps, limitations, and design decisions that have shaped the development of this project.

## 4.1 Accessibility in Digital Travel Tools

The increasing adoption of mobile travel applications has brought undeniable convenience, but also new accessibility challenges that remain underexplored. While many apps aim to provide convenience and flexibility to the general population, they often overlook the specific needs of users with mobility, sensory, or cognitive impairments.

Despite ongoing improvements in user interface standards and compliance frameworks such as the Web Content Accessibility Guidelines (**WCAG** [6]), many mainstream platforms still fail to deliver meaningful accessibility features. Studies and industry reports indicate that apps in the tourism and transportation sectors tend to treat accessibility as an afterthought, with vague icons or labels such as “wheelchair accessible” often lacking verification or context.

Popular travel-related apps such as Google Maps, Booking.com, or GetYourGuide frequently omit detailed accessibility data or provide inconsistent coverage across destinations. In real-world use, this leads to significant friction for users who must rely on fragmented or outdated sources to make confident travel decisions.

According to the WebAIM Million report (2023), over 95% of tested homepages failed to meet WCAG compliance, underscoring the widespread neglect of accessibility in digital platforms.

Among the most frequently cited issues in existing systems are:

- Lack of detailed accessibility metadata
- Inconsistent terminology or filtering
- Poor support for assistive technologies or screen readers

Given these limitations, accessibility researchers and organizations have emphasized the need to embed inclusive principles directly into the core of digital service design — especially in systems involving physical navigation, planning, and location-based decision-making.

## 4.2 Gaps in Mainstream Travel Planning Applications

Travel applications have transformed the way users plan, book, and experience their trips. Platforms such as Google Maps, Booking.com, TripAdvisor, and GetYourGuide have evolved from simple tools into comprehensive ecosystems that handle navigation, reservations, and real-time updates. Their widespread adoption has made them essential for modern travel experiences.

Despite their popularity, many of these platforms lack robust support for accessibility-related data. Accessibility indicators, when present, are often limited to generic tags like “wheelchair accessible,” without further clarification or validation. In many cases, this leads to uncertainty or even misinformed decisions for users with specific needs.

This gap not only limits the autonomy of travelers with disabilities but may also result in wasted time, unnecessary stress, or even complete inaccessibility upon arrival. The lack of actionable, user-driven accessibility insights represents a major usability shortcoming in these otherwise advanced platforms.

By identifying and analyzing these limitations, this thesis seeks to demonstrate how accessibility should not be treated as a secondary feature but rather as a fundamental design requirement for any travel planning system.

## 4.3 Inclusive Filtering and Personalized Itinerary Generation

Inclusive filtering refers to the practice of adapting digital content or recommendations based on individual accessibility needs, ensuring that the experience aligns with the user's physical, sensory, or cognitive constraints. In the context of travel planning, this involves more than just displaying general recommendations — it requires actively shaping results based on the user’s stated accessibility requirements.

Mainstream trip planning applications typically apply filters for general preferences such as price, rating, or distance. However, accessibility filters are often limited, binary, or inconsistently applied. Few systems integrate personal accessibility profiles that persist across sessions or influence search and itinerary generation logic in a meaningful way.

The approach adopted in this thesis integrates accessibility filtering with itinerary construction. Users can select specific destinations and save accessible plans tailored to their needs. This interaction model draws from best practices in inclusive UX design and is informed by real accessibility data and community input.

Due to the limited adoption of such techniques in current consumer tools, this project relies on user-centered design principles and academic literature on accessibility to define its functional scope. The Inclusive Trip Planner prototype seeks to validate the viability and effectiveness of personalized filtering when applied to mobile travel planning.

## 4.4 Conclusions of the State of the Art

As a final observation, it is evident that although mobile travel applications have become indispensable, they often exclude users with disabilities due to insufficient accessibility integration. This thesis addresses that gap by proposing a model in which personalized, accessibility-aware itinerary generation becomes a core functionality — not a secondary feature.

This gap is especially evident in tools that involve decision-making, navigation, and personalized recommendations. Although general accessibility standards such as WCAG exist, their application in mobile travel apps remains fragmented, often reduced to surface-level tags or generic filters.

There is also a lack of research and implementation focused specifically on accessibility-aware itinerary generation — a feature that could greatly improve autonomy and confidence for users. This project contributes to addressing that gap by validating a practical, user-centered approach to inclusive trip planning.

# 5. Hypothesis

## 5.1 Main Hypothesis

The main hypothesis explored in this document is that it is possible to design and implement a mobile application prototype that supports inclusive trip planning by integrating personalized accessibility filtering and curated itineraries. This solution, even in its minimum viable form, should be capable of improving the clarity, autonomy, and confidence of users when organizing accessible trips.

The hypothesis assumes that integrating accessibility-aware filters, user preferences, and itinerary components into a single interface enables better planning outcomes for users with specific mobility or sensory needs. The effectiveness of this integration will be evaluated through the functional completeness of the prototype and its alignment with the intended user flows.

## 5.2 Supporting Assumptions

To support the main hypothesis, the following secondary assumptions were defined and guided the implementation process:

### Usability improvement through personalized filtering

By allowing users to select accessibility needs (e.g. presence of ramps, elevators, braille signage), and applying those preferences directly to trip suggestions, the application can reduce uncertainty and manual research during the planning process. This personalized filtering is expected to offer higher relevance and clarity compared to generic “accessible” labels.

### Feasibility of itinerary construction based on real data

Even with limited scope and a curated list of destinations, the application should be able to demonstrate that itinerary steps can be dynamically selected and filtered in a way that respects the user’s accessibility requirements. This assumption supports the technical viability of expanding the model to a larger set of destinations in future iterations.

These hypotheses form the basis for the implementation strategy and define the success criteria by which the prototype’s value will be assessed.

# 6. Thesis

## 6.1 Central Thesis Statement

This thesis demonstrates that even a limited implementation of accessibility filtering and itinerary generation can support inclusive trip planning, using current mobile technologies. The project proposes the development and evaluation of a mobile application focused on enabling users with mobility or sensory impairments to make informed travel decisions by combining real accessibility data, user preferences, and community-generated reviews.

The proposed system is composed of two primary components: a native Android application and a backend API. The mobile frontend allows users to select destinations, apply accessibility-related filters (such as ramps, elevators, or braille signage), and save personalized itineraries. The backend, implemented in Spring Boot with a PostgreSQL database, manages user preferences, itineraries, and accessibility data. Communication between both components is handled via RESTful endpoints.

Unlike traditional trip planning tools, this system integrates accessibility filtering directly into itinerary construction. Users are guided through a structured onboarding process where they define their accessibility needs, which then inform the logic behind destination filtering and itinerary suggestions. The user interacts with the system by selecting their preferences, exploring filtered destinations, and saving complete travel plans based on those criteria. The itinerary steps are curated and stored in the backend, allowing for reusable trip structures adapted to each user.

While the current prototype offers a limited set of features centered around static itineraries and predefined accessibility filters, it successfully demonstrates the core logic required to build a more dynamic, accessibility-aware travel planning system in future iterations.

## 6.2 Justification of the Thesis

In the context of inclusive digital services, accessibility in travel and navigation tools remains a persistent challenge. Existing applications offer limited support for users with disabilities, often reducing accessibility to ambiguous labels or hidden options within settings menus. These limitations lead to uncertainty and discourage independent exploration.

This project aims to demonstrate that mobile applications can embed accessibility as a central component of the travel planning experience. By creating specific itinerary suggestions and filters aligned with user-specific access needs, the system helps reduce the informational burden placed on users who currently depend on fragmented sources and personal trial-and-error.

The system presented in this thesis is not intended as a final product but as a validated proof of concept. Its development shows that it is possible to integrate inclusive design principles into core user flows such as onboarding, searching, and planning. The structure and logic of the app are designed to be extensible, with the potential to scale up to larger datasets, more cities, and dynamic real-time content in future versions.

This work contributes to the broader field of accessibility in mobile systems by providing an applied model that demonstrates the integration of inclusive design principles in a real-world use case.

# 7. Project Management

This section documents the project management strategy applied during the development of the thesis, ensuring that each phase was carried out according to defined goals and deadlines.

## 7.1 Milestones

The following milestones represent the key stages in the development of the Inclusive Trip Planner thesis project. Each milestone marks a critical point in the project’s timeline, from initial documentation through final delivery and presentation.

- **Milestone 1: Project kickoff and scope definition**

  - **Date:** January 15, 2025
  - **Description:** The project was officially approved, and its objectives were formalized. Functional and technical specifications were drafted, along with flowcharts to define user journeys and system architecture.

- **Milestone 2: Initial implementation of the mobile frontend**

  - **Date:** February 20, 2025
  - **Description:** The mobile app’s structure was implemented using Kotlin and Jetpack Compose. Navigation and primary screens were created directly in code to reflect the planned workflows.

- **Milestone 3: Completion of frontend visual design and UX**

  - **Date:** March 25, 2025
  - **Description:** Final visual polish of the frontend interface. A unified design language, improved navigation elements, and component restructuring were applied to complete a production-quality mobile UI.

- **Milestone 4: Backend setup and architectural definition**

  - **Date:** March 30, 2025
  - **Description:** Spring Boot, PostgreSQL, and Docker technologies were configured. Initial backend project structure, database schema, and basic endpoints were defined and prepared for development.

- **Milestone 5: Full backend development and integration**

  - **Date:** April 22, 2025
  - **Description:** All core backend services and endpoints were implemented, tested, and connected to the mobile frontend. Data flows for users, itineraries, accessibility features, and saved trips were successfully integrated.

- **Milestone 6: Authentication and final feature implementation**

  - **Date:** May 12, 2025
  - **Description:**  **Firebase Authentication** [7] was added with Google and Facebook login. The frontend was adapted to handle token-based sessions, and the app reached feature-complete status.

- **Milestone 7: Final app testing and demo preparation**

  - **Date:** May 29, 2025
  - **Description:** Final bugs were resolved, and the UI was polished. Backend security and error handling were finalized. The app was tested for full demo functionality in anticipation of submission.

- **Milestone 8: Thesis document completion and submission**

  - **Date:** June 10, 2025
  - **Description:** The written thesis was finalized and submitted, including complete documentation, architecture diagrams, implementation details, and development analysis.

- **Milestone 9: Oral presentation and project defense**
  - **Date:** June 24, 2025
  - **Description:** Oral defense of the Inclusive Trip Planner thesis before the examination committee. Slides, demo, and supporting materials were prepared to communicate the project’s goals, process, and results.

## 7.2 Tasks

To successfully achieve the project milestones, the following specific tasks were carried out during each phase of the development process:

### 1. Documentation and Planning

- Define project scope, user goals, and accessibility focus.
- Draft the functional and technical specifications.
- Create flowcharts and visual diagrams to outline architecture and app structure.
- Establish the technology stack and project setup roadmap.

### 2. Frontend Development

- Configure the Android Studio development environment.
- Implement navigation and screen flow using Kotlin and Jetpack Compose.
- Build all core screens: sign up flow screens, home screen, itinerary details, itinerary steps and profile screen.
- Design and apply a consistent color scheme and visual hierarchy.
- Introduce UI components such as cards, progress bars, and accessibility indicators.

### 3. Backend Development

- Set up backend infrastructure using Spring Boot, PostgreSQL, and Docker.
- Design entities, DTOs, and database schemas aligned with frontend data models.
- Develop all core API endpoints (users, itineraries, accessibility features, etc.).
- Write integration tests to validate endpoint correctness.
- Populate the database with demo data for frontend testing.

### 4. Frontend-Backend Integration

- Integrate **Retrofit** [9](#9-retrofit) for HTTP requests from the mobile app.
- Synchronize frontend models with backend DTOs and **ViewModels** [10](#10-viewmodel).
- Implement live data fetching and display within UI components.
- Adapt screens to dynamically reflect backend data in real time.

### 5. Authentication Implementation

- Integrate Firebase Authentication into the app and backend.
- Implement Google and Facebook login using QAuth.
- Validate authentication tokens and manage secure user sessions.
- Handle login state on the frontend and personalize content.

### 6. Testing and Finalization

- Conduct integration tests and manual testing of all features.
- Identify and fix bugs in authentication, navigation, and display logic.
- Perform UI polish and final feature alignment for demo-readiness.
- Review application stability and ensure clean deployment.

### 7. Documentation and Presentation

- Draft and edit the full thesis document based on development milestones.
- Capture screenshots, write descriptions, and annotate architectural components.
- Prepare presentation slides and rehearse the final defense.
- Submit the final written thesis by June 10, 2025, and present on June 24, 2025.

## 7.3 Gantt Chart

The Gantt chart below provides a visual representation of the Inclusive Trip Planner’s project management timeline. It outlines the key phases of development, from initial documentation through final submission and presentation. Each task is distributed across the corresponding period based on its start and end dates, and grouped by work package.

The chart illustrates the sequential and sometimes overlapping nature of tasks such as frontend development, backend setup, authentication, integration, and documentation. This visual planning helped ensure that the project remained on schedule and aligned with the academic milestones leading up to the final defense.

![Gantt Chart showing the project timeline for the Inclusive Trip Planner](/documents/images/Figure1.png)

**Figure 1** – Project timeline (January to June 2025) with task phases for the Inclusive Trip Planner.

# 8. Methodology

This section outlines the methods, procedures, and technologies used throughout the development of the Inclusive Trip Planner. It details the approach taken during the design, implementation, and evaluation of the project, as well as the tools selected to support each development phase.

## 8.1 Application Design

The design process encompassed all key phases required for the successful development of the application, from early research to testing and validation. The process was structured as follows:

1. **Research Phase**

   - Collection and analysis of existing travel planning applications, with particular focus on accessibility-related limitations.
   - Definition of user personas to represent common accessibility challenges.
   - Consolidation of requirements to guide inclusive and user-centered design.

2. **Development Phase**

   - Construction of the mobile application interface based on inclusive UX principles.
   - Implementation of core features such as user profiles, itinerary saving, accessibility-based filtering, and review submission.
   - Definition of the communication structure between frontend and backend for data synchronization and persistence.

3. **Testing and Evaluation Phase**

   - Verification of system functionality through structured integration and user-flow testing.
   - Identification and resolution of issues related to navigation, authentication, and data consistency.
   - Final review and polish to ensure the application operated smoothly and reflected the intended user experience.

4. **Scalability and Improvement Phase**
   - Analysis of current MVP limitations and identification of potential enhancements.
   - Consideration of features such as support for additional cities, multilingual options, and gamification.
   - Documentation of expansion opportunities aligned with the project’s inclusive travel mission.

## 8.2 Tools and Technologies

The development of this thesis project relied on the following tools and technologies, grouped by environment and system components:

_Development Environments_

- **Frontend Development:** The mobile application was developed in Android Studio (version Giraffe), using Kotlin and Jetpack Compose as the primary language and UI framework.
- **Backend Development:** The backend was built in IntelliJ IDEA using Java and Spring Boot, with project initialization handled through Spring Initializr. PostgreSQL served as the database engine, managed through DBeaver.
- **Version Control & Collaboration:** Git was used for version control, with GitHub as the remote repository host.
- **Containerization:** Docker was used to containerize the backend application and database for local testing and deployment consistency.

_Technologies_

- **Mobile Application:** The mobile app was implemented as a native Android application targeting Android 10 (API 29) and later versions. Jetpack Compose was used to build a declarative, component-based interface.
- **Backend and Database:** Spring Boot was chosen for the backend framework due to its modularity and support for RESTful services. PostgreSQL was used as the relational database, and **Liquibase** [8](#8-liquibase) was integrated to manage schema versioning.
- **Authentication and Security:** Firebase Authentication was used to support Google and Facebook login. The backend verified ID tokens issued by Firebase and handles session validation.
- **API Integration:** The frontend used Retrofit to communicate with the backend via HTTP requests, and ViewModels were used to handle asynchronous data flow.
- **Testing and Debugging:** Postman was used to test REST endpoints. Integration tests were written in the backend to validate database persistence and service logic.

_System Components_

For the purpose of testing and demonstration, the following virtual or physical components were used to emulate end-user behavior and system interaction:

- **Android Emulator:**

  - **Device Model:** Pixel 5 (emulated)
  - **Technical Specifications:**
    - Display: 6.0 inches
    - Resolution: 2340 × 1080 pixels
    - CPU Cores: 8
    - RAM: 4 GB
    - Android Version: 13

- **Smartphone (Physical Test Device):**
  - **Device Model:** Pixel 7
  - **Technical Specifications:**
    - Display: 6.3 inches, 2400 × 1080 pixels
    - CPU: Google Tensor G2
    - RAM: 8 GB
    - Storage: 128 GB
    - Android Version: 13

## 8.3 System Architecture

The system architecture of the Inclusive Trip Planner consists of a frontend mobile application and a backend server, which communicate via a RESTful API and are designed to support personalized travel planning with accessibility-focused features. A visual representation is provided in **Figure 2**.

- **Mobile Application (Frontend):**

  - **Description:** The mobile app is a native Android application developed using Kotlin and Jetpack Compose, as outlined in Section 8.2. It allows users to register, log in, select their accessibility needs, and browse or save inclusive travel itineraries.
  - **Functionality:** All user interactions are handled locally through a declarative interface. When data is required, such as loading destinations or submitting reviews, the app performs HTTP requests to the backend via Retrofit. Authentication is managed using Firebase Identity Tokens, allowing secure login with Google or Facebook.

- **Backend Server (Spring Boot):**

  - **Description:** The backend is developed using Java and Spring Boot, exposing a REST API to handle all business logic, data persistence, and session management.
  - **Functionality:** The backend processes requests related to itineraries, accessibility features, saved trips, and user preferences. It validates Firebase tokens to authenticate users, stores structured data in a PostgreSQL database, and returns formatted DTOs to the frontend.

- **Data Flow and Integration Layer:**
  - **Description:** All data transactions between the frontend and backend are facilitated by REST endpoints. The frontend uses Retrofit and ViewModels to asynchronously process JSON responses and update UI components in real time.
  - **Functionality:** Key operations include fetching user-specific itineraries, filtering accessible destinations, submitting trip reviews, and maintaining saved itinerary lists. These exchanges rely on clearly defined models and endpoint specifications.

![Figure 2. Inclusive Trip Planner – System Architecture](/documents/images/Figure2.png)

**Figure 2** – System architecture of the Inclusive Trip Planner

# 9 Development

## 9.1 Functional Architecture and Itinerary Design

**Design rationale**  
The _Inclusive Trip Planner_ is structured to support accessibility-centric trip planning from the moment a user enters the application. Although itinerary personalization based on user-selected accessibility filters is a planned feature, the current implementation focuses on delivering static yet meaningful itineraries that are pre-curated and stored in the backend. These itineraries are designed to represent common accessible tourist paths, each of them are composed of multiple steps associated with a specific destination and accessibility metadata.

The filtering feature, when implemented, will use the accessibility preferences selected by the user during the onboarding flow or updated in their profile. These preferences are stored in a linking table (`user_accessibility_features`) and will eventually serve as the basis for showing only itineraries that include matching accessibility features (e.g. wheelchair access, braille signage, visual guidance). Given the current scope and the small dataset (five itineraries), this logic was deferred for future releases.

**Application structure**  
The system is divided into two principal components:

- **Frontend**: A native Android application built with Kotlin and Jetpack Compose. It provides the user interface for browsing itineraries, selecting accessibility preferences, saving trips, and submitting reviews. All HTTP requests are made using Retrofit, and the state is managed via ViewModels  and **StateFlow** [11](#11-stateflow).


- **Backend**: A Spring Boot API that manages core logic and data persistence. It exposes REST endpoints for all major operations and communicates with a PostgreSQL database. The backend includes full CRUD operations for itineraries, destinations, and accessibility features.

The frontend and backend interact exclusively through JSON-based HTTP requests. Each itinerary shown in the app is retrieved from the backend using a unique identifier, with full metadata loaded on demand. The data is deserialized into frontend models and observed through reactive ViewModels.

**Itinerary model**  
Each itinerary in the backend is composed of:

- A title and description
- A destination reference
- A list of ordered steps, each representing a point of interest
- An optional list of accessibility features (e.g. "ramp access", "visual signage")
- User-generated reviews and ratings

The itineraries are defined in the database with the following fields:

```sql
CREATE TABLE itineraries (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    title VARCHAR NOT NULL,
    description TEXT,
    price DECIMAL,
    duration INTEGER,
    rating FLOAT,
    destination_id UUID REFERENCES destinations(id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

Accessibility features linked to each itinerary are stored in a join table:

```sql
CREATE TABLE itinerary_accessibility (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    itinerary_id UUID REFERENCES itineraries(id),
    feature_id UUID REFERENCES accessibility_features(id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE (itinerary_id, feature_id)
);
```

Each user’s preferences are similarly stored:

```sql
CREATE TABLE user_accessibility_features (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    user_id UUID REFERENCES users(id),
    feature_id UUID REFERENCES accessibility_features(id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE (user_id, feature_id)
);
```

**Backend integration and population**  
The initial itineraries were created manually and inserted into the PostgreSQL database using Liquibase changelogs. Each itinerary is associated with real points of interest in Paris and includes pre-set accessibility features.

Example snippet of itinerary creation:

```xml
<insert tableName="itineraries">
    <column name="id" value="11111111-1111-1111-1111-111111111111"/>
    <column name="title" value="Louvre Museum Tour"/>
    <column name="description" value="Discover world-famous art inside the Louvre."/>
    <column name="price" valueNumeric="120.00"/>
    <column name="duration" valueNumeric="3"/>
    <column name="rating" valueNumeric="4.8"/>
    <column name="destination_id" value="00000000-0000-0000-0000-000000000201"/>
    <column name="created_at" valueDate="2024-01-01T10:00:00"/>
    <column name="updated_at" valueDate="2024-01-01T10:00:00"/>
</insert>
```

**Frontend integration**  
On the frontend, itineraries are retrieved from the backend via a Retrofit API interface:

```kotlin
interface ItineraryApi {
    @GET("/api/itineraries")
    suspend fun getAllItineraries(): List<Itinerary>
}
```

The data is stored and exposed through a ViewModel:

```kotlin
class ItineraryViewModel : ViewModel() {
    private val _itineraries = MutableStateFlow<List<Itinerary>>(emptyList())
    val itineraries: StateFlow<List<Itinerary>> = _itineraries

    init {
        fetchItineraries()
    }

    fun fetchItineraries() {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.itineraryApi.getAllItineraries()
                _itineraries.value = response
            } catch (e: Exception) {
                // Error handling
            }
        }
    }
}
```

Each itinerary is displayed through UI components such as cards or scrollable lists, depending on the screen context.

**Planned filtering extension**  
While itinerary filtering is not yet live, it is conceptually straightforward to implement. The idea is to retrieve a user’s selected `feature_id`s, then query only those itineraries that include all matching features. This can be done in the backend by intersecting the `user_accessibility_features` and `itinerary_accessibility` tables and returning only matching itinerary IDs.

A simplified diagram for the future data flow might look like this:

![Figure 3. Inclusive Trip Planner – System Architecture](/documents/images/Figure3.png)

## 9.2 Itinerary Flow and Feature Implementation

**User Interaction Flows**  
Once users complete the onboarding process, they are directed to the home screen where curated itineraries are presented. These itineraries are displayed as scrollable cards with summarized content and can be explored in more detail through intuitive navigation. The following flows represent how users interact with itineraries throughout the app:

- **Browsing**: The home screen presents all available itineraries in a scrollable list. Each item includes a title, duration, rating, and accessibility indicators. Tapping on a card opens the detailed itinerary view.

- **Viewing Details**: The itinerary details screen presents a full overview of the selected itinerary, including a description, estimated price, accessibility features, and a visual breakdown of its steps.

- **Saving for Later**: Users can tap the bookmark icon to save an itinerary to their profile. This action triggers a request to the backend and persists the itinerary as a "saved trip" in the database.

- **Next Plan**: Once saved, a user can designate one itinerary as their “next plan.” This feature allows for quick access to the selected trip from the top of the home screen and visually highlights it with a dedicated card.

- **Reviewing**: After completing a trip or viewing its content, users may leave a review. The review includes a numerical rating and optional written feedback, often centered on the itinerary’s accessibility quality.

Each of these flows is designed to be direct and frictionless, with loading states handled through `ViewModel` observation and backend communication managed via Retrofit.

These features are currently implemented across three primary screens:

- The **Home Screen** highlights the “Next Plan” at the top and lists all available itineraries below.
- The **Itinerary Details Screen** allows users to review full content and take actions such as saving or reviewing.
- The **Saved Itineraries Screen** serves as a personal archive, letting users revisit or replace their planned trip.

**Saving an Itinerary**  
The process of saving an itinerary allows users to bookmark travel plans they are interested in revisiting or executing. This feature is central to the personalized experience of the app, enabling users to build a shortlist of accessible itineraries.

When a user taps the save icon on an itinerary details screen, the app performs the following steps:

1. **Frontend Action**: The user’s action triggers a call to the `SavedItineraryViewModel`, which communicates with the Retrofit API interface.
2. **API Request**: A POST request is sent to the backend endpoint `/api/saved-itineraries` with a payload containing the user’s ID and the selected itinerary ID.
3. **Backend Processing**: The backend validates the request, ensures that the combination of user and itinerary does not already exist, and persists the record in the `saved_itineraries` table.
4. **Response and UI Update**: Upon success, the frontend updates the saved state locally, which is reflected in the UI by toggling the save icon.

This interaction is handled asynchronously using Kotlin coroutines, ensuring that the app remains responsive during the request.

![Flowchart – Save Itinerary](/documents/images/Figure4.png)
_Flowchart: Save itinerary interaction from user input to UI update_

Saved itineraries are retrieved later for display in both the **Home Screen** (for the “Next Plan”) and the **Saved Itineraries Screen**...

**Java** – `SavedItineraryController#create()` (Spring Boot backend endpoint)

```java

@PostMapping
public SavedItineraryResponse create(@RequestBody SavedItineraryRequest request) {
    SavedItinerary saved = service.create(request);
    return SavedItineraryResponse.from(saved);
}

```

**Kotlin** – `SavedItinerariesViewModel#saveItinerary()` (ViewModel Retrofit call)


```kotlin
fun saveItinerary(itineraryId: UUID, onSuccess: (() -> Unit)? = null) {
    viewModelScope.launch {
        try {
            val request = SavedItineraryRequest(userId = userId, itineraryId = itineraryId)
            val created = RetrofitClient.savedItineraryApi.saveItinerary(request)
            _savedItineraries.value = _savedItineraries.value + created
            onSuccess?.invoke()
        } catch (e: Exception) {
            Log.e("SavedItineraries", "Save error: ${e.message}", e)
        }
    }
}

```

Saved itineraries are retrieved later for display in both the **Home Screen** (for the “Next Plan”) and the **Saved Itineraries Screen**. A GET request is sent to `/api/users/{userId}/saved-itineraries`, and the resulting list is deserialized and rendered as a list of cards or tiles.


**Next Plan Functionality**  
The “Next Plan” feature provides users with a quick-access view of their most recent saved itinerary, displayed prominently on the Home Screen. This functionality is designed to streamline access to upcoming trips and reinforce continuity in the user experience.

When a user marks a saved itinerary as their next plan, the following flow is triggered:

1. **Frontend Trigger**: From any itinerary screen, a user can select the “Set as Next Plan” option (typically integrated into the save flow).
2. **In-Memory Assignment**: The frontend searches the current list of saved itineraries and updates the `nextPlan` state in the `SavedItinerariesViewModel`.
3. **Display**: On the Home Screen, the `NextPlanSection` composable reacts to this state change and displays the selected itinerary with relevant information (title, location, price, duration, and accessibility tags).


**Kotlin** – `SavedItinerariesViewModel#setAsNextPlan()` (in-memory assignment)

```kotlin
fun setAsNextPlan(itineraryId: UUID) {
    val item = _savedItineraries.value.find { it.itineraryId == itineraryId }
    if (item != null) {
        _nextPlan.value = item
    }
}
```

**Kotlin** – `NextPlanSection()` (Composable rendering logic)

```kotlin
@Composable
fun NextPlanSection(
    navController: NavHostController,
    savedViewModel: SavedItinerariesViewModel,
    itineraryViewModel: ItineraryViewModel
) {
    val nextPlan = savedViewModel.nextPlan.collectAsState().value
    val itinerary by itineraryViewModel
        .getItineraryById(nextPlan?.itineraryId?.toString() ?: "")
        .collectAsState(initial = null)

    if (nextPlan != null && itinerary != null) {
        RecommendedDestinationCard(
            title = itinerary.title ?: "Untitled",
            location = itinerary.destinationName ?: "Coming soon",
            rating = itinerary.rating?.toDouble() ?: 0.0,
            price = itinerary.price?.toString() ?: "Free",
            duration = "${itinerary.duration ?: "?"} hours",
            people = 2,
            imageUrl = itinerary.imageUrl,
            accessibilityFeatures = listOf("Wheelchair Accessible", "Braille Available"),
            onClick = {
                navController.navigate("itinerary_details/${itinerary.id}")
            }
        )
    }
}
```

The current implementation stores the ‘Next Plan’ in memory. The plan is rendered using the same `RecommendedDestinationCard` UI component used elsewhere in the app, ensuring design consistency.

If no itinerary has been set as the next plan, the section shows a fallback message and offers a call-to-action button leading to the itinerary search screen.


**Reviewing an Itinerary**  
The review system enables users to provide feedback on itineraries they’ve viewed or completed, helping improve transparency, quality, and trust in the experience. Reviews can include a numeric rating and a short comment, with optional anonymity.

This feature is split across three key screens:

- **Itinerary Details Screen**: Users can browse all reviews associated with the current itinerary and submit a new review via a dedicated button.
- **Write a Review Screen**: Provides a form to input rating, comment, name (optional), and anonymity flag.
- **Profile Screen**: Users can view their own past activity, including submitted reviews, accessible from the “My Activity” section.

**Kotlin** – `ReviewCard()` Composable (renders a review)

```kotlin
@Composable
fun ReviewCard(review: Review, navController: NavHostController, showItineraryButton: Boolean) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        elevation = 4.dp,
        shape = RoundedCornerShape(12.dp),
        backgroundColor = Color.White
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Anonymous", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Row {
                repeat(review.rating.toInt()) {
                    Icon(Icons.Filled.Star, contentDescription = null, tint = Color(0xFFFFD700))
                }
            }
            Text(review.comment, fontSize = 14.sp)
        }
    }
}
```
**Kotlin** – `WriteAReviewScreen()` logic: Request submission

```kotlin
val request = ReviewRequest(
    userId = userId,
    itineraryId = UUID.fromString(itineraryId),
    rating = rating.toFloat(),
    comment = reviewText.trim()
)
reviewViewModel.postReview(
    request,
    onSuccess = {
        navController.popBackStack()
    },
    onError = { error -> errorMessage = error ?: "Error posting review" }
)
```

Reviews are submitted through a `ReviewViewModel`, which manages the creation and retrieval of reviews using Retrofit. When the form is validated and submitted, a `ReviewRequest` is sent to the backend, and the newly created review is added to the current state.

Each review contains:

- The **user ID** (or anonymous flag)
- The **itinerary ID**
- A **rating** (1–5 stars)
- A **comment** (up to 255 characters)
- A **timestamp** of creation


**Kotlin** – `ReviewViewModel#postReview()` (Retrofit call and state update)

```kotlin
fun postReview(
    request: ReviewRequest,
    onSuccess: () -> Unit,
    onError: (String?) -> Unit
) {
    viewModelScope.launch {
        try {
            val response = RetrofitClient.reviewApi.postReview(request)
            _reviews.value = _reviews.value + response
            onSuccess()
        } catch (e: Exception) {
            Log.e("ReviewViewModel", "Error posting review: ${e.message}", e)
            onError(e.message)
        }
    }
}
```

Reviews are shown inline using the `ReviewCard()` component, which renders the rating visually with stars, the comment text, and the creation date. Each review is rendered in the **Itinerary Details Screen** using a `LazyColumn`, and users can submit a new review through a clearly visible call-to-action button.

At this stage, all reviews are fetched at once on app load and filtered client-side per itinerary. This approach is sufficient for the demo scale, but may evolve into paginated or on-demand loading for larger datasets.

**Overview of User Interface Screens**  
The Inclusive Trip Planner frontend is composed of multiple screens, each dedicated to a specific function in the user journey. The interface is built using Jetpack Compose and follows a component-driven, reactive design based on ViewModels and StateFlow.

Below is an overview of the main screens implemented in the current version of the app:

**User Interface Screens**

▸ **Onboarding Flow**
- SignUpNameScreen.kt
- SignUpEmailScreen.kt
- SignUpPhoneScreen.kt
- SignUpPasswordScreen.kt
- SignUpCountriesScreen.kt
- SignUpDestinationsScreen.kt
- SignUpAccessibilityScreen.kt

▸ **Core Itinerary Screens**
- HomeScreen.kt
- SearchScreen.kt
- SavedItinerariesScreen.kt
- ItineraryDetailsScreen.kt
- ItineraryStepsScreen.kt

▸ **Review & Feedback**
- WriteAReviewScreen.kt
- MyReviewsScreen.kt

▸ **Account & Profile**
- ProfileScreen.kt
- SettingsScreen.kt

▸ **Authentication & Recovery**
- LoginWithPasswordScreen.kt
- ForgotPasswordScreen.kt
- ForgotAccountScreen.kt
- ChangePasswordScreen.kt
- EmailScreen.kt
- PhoneScreen.kt
- OTPVerificationScreen.kt

▸ **Other Utility Screens**
- NotificationsScreen.kt
- PrivacyPolicyScreen.kt
- WelcomeScreen.kt

## 9.3 Backend Architecture and Security

### Entity Structure

The backend of the Inclusive Trip Planner is implemented using Spring Boot and follows a consistent layered design. Every core feature — such as users, settings, or itineraries — is structured across five components:

1. **Model**: Defines the database schema and lifecycle behavior using JPA.
2. **DTOs**: Separate request and response structures to avoid exposing internal entity logic.
3. **Repository**: Interfaces for interacting with the database, with custom methods where necessary.
4. **Service**: Contains business logic, validation, and entity transformations.
5. **Controller**: Exposes the API endpoints and delegates to the service layer.

The architecture encourages separation of concerns and makes each module easier to test, extend, and debug. Below, we present a simplified overview of two representative entities: **User** and **Setting**.

---

#### User Entity

The User entity models a registered person in the system and includes fields for platform-based authentication and contact data. The entity uses lifecycle annotations to populate timestamps automatically.

– Example excerpt from the User model:
```java
@Entity
@Table(name = "users")
public class User {
    @Id @GeneratedValue private UUID id;

    @Column(nullable = false) private String name;
    @Column(nullable = false, unique = true) private String email;
    @Column(name = "password_hash") private String passwordHash;
    private String platform;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
```

Data Transfer Objects (DTOs) are used to accept input from the frontend and return sanitized responses. Mapping is done manually for clarity.

– Request DTO for creating a user:
```java
@Data
@Builder
public class UserRequest {
    private String name;
    private String email;
    private String passwordHash;
    private String platform;
}
```

Custom repository methods allow queries by fields like email or phone:

– User repository with a custom lookup:
```java
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
}
```

The service layer handles business logic, including validation and password encoding. The example below enforces different rules depending on the platform type:

– Excerpt from UserService:
```java
@Transactional
public User createUser(UserRequest dto) {
    if ("EMAIL".equals(dto.getPlatform())) {
        String hash = passwordEncoder.encode(dto.getPasswordHash());
        return userRepository.save(User.builder()
            .email(dto.getEmail())
            .passwordHash(hash)
            .name(dto.getName())
            .platform("EMAIL")
            .build());
    }
    throw new IllegalArgumentException("Unsupported platform");
}
```

---

#### Setting Entity

The Setting entity stores configurable app preferences, such as notification toggles or labels. It uses a unique key to distinguish settings.

– Excerpt from the Setting model:
```java
@Entity
@Table(name = "settings")
public class Setting {
    @Id @GeneratedValue private UUID id;

    @Column(name = "setting_key", nullable = false, unique = true)
    private String settingKey;

    private String label;
    private boolean defaultValue;
}
```

As with other entities, the service creates and validates the setting, mapping values from a DTO:

– Excerpt from SettingService:
```java
@Transactional
public Setting createSetting(SettingRequest dto) {
    return settingRepository.save(Setting.builder()
        .settingKey(dto.getSettingKey())
        .label(dto.getLabel())
        .defaultValue(dto.isDefaultValue())
        .build());
}
```

This structure is applied consistently across the backend.

### Integration Testing Approach

The Inclusive Trip Planner backend includes integration tests for every major entity to ensure API reliability and prevent regressions. These tests verify full request-response cycles using a real Spring Boot context and an in-memory H2 database.

Each test class:

- Runs with `@SpringBootTest` and `@AutoConfigureMockMvc`
- Operates in isolation using `@BeforeEach` to reset the database
- Includes JWT-based authentication to simulate protected API access
- Validates standard CRUD operations through actual HTTP requests

The H2 database is used as an in-memory relational engine during testing. This allows for fast and isolated test execution without relying on an external database. It's configured through a dedicated `application-test.yml` profile, which disables Liquibase migrations and sets up schema generation directly from the entity annotations.

**application-test.yml** – Minimal setup for H2 testing:

```yaml
spring:
  datasource:
    url: jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1
    driverClassName: org.h2.Driver
    username: sa
    password:

  jpa:
    database-platform: org.hibernate.dialect.H2Dialect
    hibernate:
      ddl-auto: update
    show-sql: true

  liquibase:
    enabled: false

logging:
  level:
    org.hibernate.SQL: DEBUG
```

Each test class is annotated with:

```java
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
```

This setup ensures each test suite uses the test profile and communicates via real HTTP using `MockMvc`.

---

#### Example: `UserServiceTest`

This test verifies basic operations for the User entity. It includes a setup step to register and log in a test user for authenticated calls.

**Creating and Fetching a User**

```java
@Test
void shouldCreateUserAndFetchItById() throws Exception {
    UserRequest request = UserRequest.builder()
        .name("Alice")
        .email("alice@example.com")
        .passwordHash("secret")
        .phone("123456")
        .build();

    MvcResult result = mockMvc.perform(post("/api/users")
            .header("Authorization", "Bearer " + token)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isOk())
        .andReturn();

    UserResponse created = objectMapper.readValue(
        result.getResponse().getContentAsString(), UserResponse.class);

    mockMvc.perform(get("/api/users/" + created.getId())
            .header("Authorization", "Bearer " + token))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.email").value("alice@example.com"));
}
```

---

#### Example: `SettingServiceTest`

The `SettingServiceTest` validates system preferences through CRUD operations.

**Creating and Validating a Setting**

```java
@Test
void shouldCreateSettingAndFetchItById() throws Exception {
    SettingRequest request = SettingRequest.builder()
        .settingKey("notify_challenges")
        .label("Notify on challenge completion")
        .description("Send alert when a challenge is completed")
        .defaultValue(true)
        .build();

    MvcResult result = mockMvc.perform(post("/api/settings")
            .header("Authorization", "Bearer " + token)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isCreated())
        .andReturn();

    SettingResponse created = objectMapper.readValue(
        result.getResponse().getContentAsString(), SettingResponse.class);

    mockMvc.perform(get("/api/settings/" + created.getId())
            .header("Authorization", "Bearer " + token))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.label").value("Notify on challenge completion"));
}
```

This structure is replicated across all other entity test classes. Authentication and security layers are fully integrated in these tests and will be detailed further in Section 9.4.

### JWT-Based Security

To secure protected API endpoints, the backend implements stateless authentication using **JWT (JSON Web Tokens)**. This ensures that every client request includes its own proof of identity without relying on session persistence.

JWT tokens are generated during login and include the user’s ID and email as embedded claims. Once issued, the token must accompany every subsequent HTTP request in the `Authorization` header.


#### Token Generation: `JwtService`

The `JwtService` class is responsible for creating and validating tokens. Tokens are signed with an HMAC SHA-256 secret key and are set to expire after 24 hours.

– Excerpt of token creation:

```java
public String generateToken(UUID userId, String email) {
    return Jwts.builder()
        .setSubject(email)
        .claim("userId", userId.toString())
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + 86400000))
        .signWith(secretKey)
        .compact();
}
```

The token includes both subject (email) and custom claims (user ID). It is signed with a secret key generated on startup and verified during authentication.


#### Token Validation: `JwtAuthenticationFilter`

To process incoming requests, the `JwtAuthenticationFilter` checks for a `Bearer` token in the `Authorization` header. If valid, it extracts the user’s email, loads the corresponding `UserDetails`, and populates the Spring Security context.

– Excerpt of authentication logic:

```java
@Override
protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {

    final String authHeader = request.getHeader("Authorization");
    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
        filterChain.doFilter(request, response);
        return;
    }

    final String token = authHeader.substring(7);
    String userEmail = jwtService.extractEmail(token);

    if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        Optional<User> userOptional = userRepository.findByEmail(userEmail);

        if (userOptional.isPresent() && jwtService.isTokenValid(token, userDetails)) {
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
    }

    filterChain.doFilter(request, response);
}
```

This filter ensures that only valid tokens can access protected endpoints and that authentication information is injected into the request lifecycle.


#### Password Logic and Platform Rules

When creating users, the backend enforces rules based on the authentication platform:

- If the user registers via `"EMAIL"`, a hashed password must be provided.
- If the user uses OAuth (e.g. Google or Facebook), passwords are not accepted.

These rules are enforced in the `UserService`:

```java
if (platform.equals("EMAIL")) {
    if (dto.getPasswordHash() == null) {
        throw new IllegalArgumentException("Password required for email users");
    }
} else {
    if (dto.getPasswordHash() != null) {
        throw new IllegalArgumentException("OAuth users must not provide a password");
    }
}
```

This ensures secure credential handling and clean separation between login strategies.


![Figure 5 – JWT Authentication Flow](/documents/images/Figure5.png)  
*JWT lifecycle showing login request, token issuance, authorization header, and backend validation*


### Spring Security Configuration

Access control for the Inclusive Trip Planner is configured through a `SecurityConfig` class. The application enforces **stateless JWT-based authentication**, meaning no sessions or cookies are used. Only explicitly defined endpoints are publicly accessible.

A custom filter is injected into the Spring Security filter chain to validate tokens before requests reach secured controllers. Additionally, unauthorized requests return a standardized 401 response.

– **Route Protection and Stateless Session Policy**:
```java
http
  .csrf(AbstractHttpConfigurer::disable)
  .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
  .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
  .exceptionHandling(e -> e.authenticationEntryPoint(unauthorizedEntryPoint()))
  .authorizeHttpRequests(auth -> auth
      .requestMatchers("/api/auth/**", "/api/users", "/api/destinations/**").permitAll()
      .anyRequest().authenticated());
```

– **Password Encryption**:
```java
@Bean
public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}
```

– **Custom Entry Point for Unauthorized Access**:
```java
@Bean
public AuthenticationEntryPoint unauthorizedEntryPoint() {
    return (request, response, authException) ->
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
}
```

These security rules apply uniformly across all endpoints. The actual token verification and user context injection are handled by the `JwtAuthenticationFilter` described earlier.

## 9.4 Authentication and Identity Integration

### Email and Password Login

The Inclusive Trip Planner supports classic login using either **email or phone number**, combined with a password. This method is handled in the backend via the `AuthService.login()` method.

Upon receiving the credentials, the system:

- Looks up the user by email or phone  
- Verifies the password using the `PasswordEncoder` (BCrypt)  
- Issues a signed JWT token if successful  
- Returns a structured response including user ID and contact information

If the credentials are invalid, the service throws an appropriate exception, and the controller returns a `400 Bad Request`.

– Core logic of `AuthService.login()`:
~~~java
public LoginResponse login(LoginRequest request) {
    User user = userRepository.findByEmail(request.getIdentifier())
        .or(() -> userRepository.findByPhone(request.getIdentifier()))
        .orElseThrow(() -> new IllegalArgumentException("User not found"));

    if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
        throw new IllegalArgumentException("Invalid password");
    }

    String token = jwtService.generateToken(user.getId(), user.getEmail());

    return LoginResponse.builder()
        .userId(user.getId())
        .name(user.getName())
        .email(user.getEmail())
        .phone(user.getPhone())
        .token(token)
        .build();
}
~~~

---

### Firebase-Based Social Login Flow (Google & Facebook)

To support authentication via **Google** or **Facebook**, the frontend integrates with **Firebase Authentication**. Once the user signs in (e.g., via Google One Tap), Firebase returns a secure ID token. This token is then sent to the backend, which verifies its authenticity and issues its own signed JWT for accessing protected resources.

This process ensures stateless, secure login without relying on traditional password handling for social accounts.

#### Login Flow Overview

1. The frontend initiates authentication using the Firebase SDK.
2. A Firebase ID token is returned after successful login.
3. The token is sent to the backend endpoint:
   - `/api/auth/google` or `/api/auth/facebook`
4. The backend verifies the token using Firebase’s public keys.
5. The backend either logs in the existing user or registers a new one.
6. A backend-generated JWT is returned for sessionless authorization.

– Example backend endpoints:
~~~java
@PostMapping("/api/auth/google")
public ResponseEntity<AuthResponse> loginWithGoogle(@RequestBody FirebaseSignInRequest request) {
    return ResponseEntity.ok(authService.loginOrRegisterWithFirebase(request.getIdToken()));
}

@PostMapping("/api/auth/facebook")
public ResponseEntity<AuthResponse> loginWithFacebook(@RequestBody FirebaseSignInRequest request) {
    return ResponseEntity.ok(authService.loginOrRegisterWithFirebase(request.getIdToken()));
}
~~~

Token verification is handled either using the Firebase Admin SDK:

~~~java
FirebaseToken decoded = FirebaseAuth.getInstance().verifyIdToken(firebaseToken);
~~~

Or through a manual verifier (see Appendix [X] for implementation using `SignedJWT` and `jwtProcessor`).

Once verified, the backend normalizes the user account and returns a secure JWT:

~~~java
public AuthResponse loginOrRegisterWithFirebase(String firebaseToken) {
    FirebaseToken decoded = firebaseTokenVerifier.verify(firebaseToken);
    String email = decoded.getEmail();
    // create or fetch user logic
    String jwt = jwtService.generateToken(user);
    return new AuthResponse(jwt, user.getName(), user.getId());
}
~~~

This unified flow is used for all Firebase-based platforms and simplifies backend logic regardless of the provider.

---

### Security and Validation Rules

The backend enforces a strict set of platform-specific validation rules to ensure safe authentication and user consistency.

#### 1. Password Requirements for Email-Based Users

Users registering with `"EMAIL"` must provide a password. It is hashed using BCrypt and validated during login.

~~~java
if (platform.equals("EMAIL")) {
    if (dto.getPasswordHash() == null) {
        throw new IllegalArgumentException("Password required for email users");
    }
}
~~~

Passwords are never stored in plaintext and are validated securely:

~~~java
if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
    throw new IllegalArgumentException("Invalid password");
}
~~~

#### 2. Rules for Social Login Users (Google or Facebook)

Users registering with `"GOOGLE"` or `"FACEBOOK"` must **not** provide a password. Their identity is verified solely through Firebase tokens.

~~~java
if (!platform.equals("EMAIL") && dto.getPasswordHash() != null) {
    throw new IllegalArgumentException("OAuth users must not provide a password");
}
~~~

This prevents hybrid accounts and enforces separation of authentication strategies.

#### 3. Centralized Validation

These platform-specific rules are enforced in both:

- `UserService.createUser()` (during registration)
- `AuthService.loginOrRegisterWithFirebase()` (during social login)

All users are normalized into a single entity model.

#### 4. Access Control via JWT

After successful login (regardless of platform), users receive a signed JWT:

- The token is required in the `Authorization` header for protected routes.
- Tokens are verified by the `JwtAuthenticationFilter`.
- The backend is fully stateless — no sessions or cookies are used.

Unauthorized access returns a standardized 401 error.

---

### Platform Rules Comparison Table

| Platform  | Login Input         | Backend Handling                          |
|-----------|---------------------|-------------------------------------------|
| EMAIL     | Email or phone + password | Hash password, validate, issue JWT     |
| GOOGLE    | Firebase ID Token   | Verify Firebase token, issue JWT          |
| FACEBOOK  | Firebase ID Token   | Same as Google (uses unified logic)       |


## 9.5 Frontend Architecture

### Architecture Overview

The Inclusive Trip Planner frontend is a **native Android application** developed with **Jetpack Compose**. It follows a unidirectional data flow where **ViewModels expose state via `StateFlow`**, which is then observed in Composable functions using `collectAsState()`. All backend communication is handled through **Retrofit**, and authentication is maintained using a local JWT token stored and reused via an **OkHttp interceptor**.

There is **no repository layer** between ViewModels and APIs. Instead, the ViewModels make direct Retrofit calls to the corresponding API interfaces, managing loading, error, and result states locally. This architecture simplifies the flow and keeps the application lightweight, which aligns with the current scope of the project.

Each screen typically binds to one or more ViewModels. These ViewModels encapsulate both logic (e.g., fetch, create, or save operations) and UI state (e.g., `isLoading`, `error`, `data`). For user-related functionality, `AndroidViewModel` is used instead of `ViewModel`, as it provides access to the application context — needed for reading tokens or persistent storage.

The overall architecture is reactive, lightweight, and modular:

- **Composable UI** reacts to state changes emitted by the ViewModels.
- **ViewModels** act as the source of truth for data and UI state.
- **RetrofitClient** centralizes all backend API endpoints.
- **TokenManager** [12](#12-tokenmanager) uses Jetpack DataStore (the modern replacement for SharedPreferences) to persist JWT tokens securely across app sessions, enabling automatic reuse on subsequent launches.
- **AuthInterceptor** [13](#13-authinterceptor) (OkHttp) reads the stored JWT and appends it to every secured request via the `Authorization` header.
- **NavController** handles screen transitions and navigation arguments.

---

![Flowchart – StateFlow-Driven UI](/documents/images/Figure6.png)  
_Flowchart: Unidirectional state flow from user input to UI recomposition_



###  ViewModel and State Handling

The Inclusive Trip Planner uses **ViewModels** to bridge UI elements and backend data. Each ViewModel encapsulates the logic for retrieving, updating, and exposing state, while keeping the UI reactive and clean.

This pattern establishes each ViewModel as the single source of truth for its feature’s state.

---

#### Purpose and Structure

Every feature in the app (e.g., itineraries, reviews, users) is backed by a dedicated `ViewModel`. These classes are responsible for:

- Fetching data from the backend via Retrofit
- Holding and exposing UI state using `StateFlow`
- Handling loading, success, and error states
- Reacting to lifecycle events

There is no intermediate repository layer — ViewModels **directly access Retrofit APIs** provided by `RetrofitClient`. This simplifies the data flow and avoids unnecessary abstraction for a project of this size.

---

#### State Management with StateFlow

State in each ViewModel is held using **`MutableStateFlow`**, and exposed as immutable `StateFlow` for observation in Composables via `collectAsState()`:

```kotlin
private val _itineraries = MutableStateFlow<List<Itinerary>>(emptyList())
val itineraries: StateFlow<List<Itinerary>> = _itineraries
```

This pattern ensures UI elements always display the latest version of the data and recompute automatically when state updates.

Most ViewModels also declare:

- `_isLoading`: a boolean flag for showing progress indicators
- `_error`: a nullable string for user-facing error messages

---

#### Lifecycle Awareness

Each ViewModel uses `viewModelScope` to launch coroutines for backend operations. This scope is automatically tied to the lifecycle of the ViewModel and avoids leaks.

```kotlin
viewModelScope.launch {
    try {
        val response = RetrofitClient.itineraryApi.getAllItineraries()
        _itineraries.value = response
    } catch (e: Exception) {
        _error.value = "Failed to load itineraries"
    }
}
```

ViewModels are initialized at screen load and persist across recompositions and configuration changes.

---

#### Reactive UI Consumption

In Jetpack Compose screens like `HomeScreen` or `ItineraryDetailsScreen`, ViewModel state is observed using `collectAsState()`:

```kotlin
val itineraries by itineraryViewModel.itineraries.collectAsState()
```

When the data inside `StateFlow` changes, the composable that reads it is automatically recomposed, keeping the UI always in sync with the backend.


**Example use cases**:

- `ItineraryViewModel` retrieves a list of static itineraries from the backend and exposes a helper method to retrieve a single itinerary by ID using `.map { ... }.collectAsState()`.
- `ReviewViewModel` fetches and filters all reviews, and updates the list locally when a new review is posted.
- `UserViewModel` handles JWT-based login and state persistence by accessing Android storage through `Context`.

This structure keeps data flow unidirectional and tightly scoped, minimizing UI complexity and side effects.


#### Specialized ViewModels

- `AndroidViewModel` is used when access to `Context` is required (e.g., `UserViewModel`, for token storage using `TokenManager`)
- Regular `ViewModel` is sufficient for most use cases (e.g., `ItineraryViewModel`, `ReviewViewModel`)

They all follow a shared internal pattern: exposing core data, loading, and error states, with default values initialized inside `init { ... }`.

---

#### Error and Result Handling

Network failures are caught in `try/catch` blocks, and the corresponding `_error` message is updated. The UI can display this error message or fallback content.

At this stage, the app does not implement a centralized error transformer or global interceptor. This decision keeps logic localized and predictable, though a unified error-handling strategy may be introduced as the app scales.

Success responses are added to the internal state immediately, as shown in `ReviewViewModel`:

```kotlin
_reviews.value = _reviews.value + response
```

This approach keeps the UI responsive and up-to-date without requiring refreshes.

---

### Retrofit and API Integration

All backend communication in the Inclusive Trip Planner is handled via **Retrofit**, a type-safe HTTP client for Android. The app defines a set of interfaces (e.g., `ItineraryApi`, `ReviewApi`, `SavedItineraryApi`) that map directly to backend endpoints. These interfaces follow standard RESTful conventions, using annotations like `@GET`, `@POST`, `@DELETE`, and `@Path`.

---

#### RetrofitClient Initialization

The `RetrofitClient` object is globally accessible and initialized once during application startup via a manual `init(context)` call. This setup ensures access to Android’s `Context` for authentication purposes.

```kotlin
object RetrofitClient {
    private const val BASE_URL = "http://10.0.2.2:8080"
    
    fun init(context: Context) {
        appContext = context.applicationContext
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor(appContext))
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val itineraryApi = retrofit.create(ItineraryApi::class.java)
    val reviewApi = retrofit.create(ReviewApi::class.java)
    // ...
}
```

This pattern simplifies endpoint access and avoids boilerplate across ViewModels. Each API interface is exposed as a singleton property and injected directly into ViewModels when needed.

---

#### Authorization with Interceptor

Authentication is managed using an **OkHttp interceptor** (`AuthInterceptor`) that retrieves the stored JWT and adds it to the `Authorization` header of every outgoing request.

```kotlin
class AuthInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = runBlocking {
            TokenManager.getToken(context).firstOrNull()
        }

        val request = if (token != null) {
            chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
        } else {
            chain.request()
        }

        return chain.proceed(request)
    }
}
```

This approach ensures consistent authentication across all API calls, without requiring each ViewModel to manually attach tokens.

---

#### Request and Response Models

Each API uses distinct DTOs for request and response operations. For example, creating a review uses ReviewRequest, while fetching data returns Review.

This separation keeps the contract between frontend and backend clear and avoids coupling UI state to transport structures.

---

#### No Dynamic Base URL or Environment Switching

As this is a first-stage MVP, the base URL is fixed to the local backend (`10.0.2.2:8080`). There is no runtime environment switching between staging/production, which is acceptable at this stage and avoids unnecessary complexity during prototyping.

```kotlin
private const val BASE_URL = "http://10.0.2.2:8080"

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .client(client)
    .addConverterFactory(GsonConverterFactory.create())
    .build()
```

In future iterations, this could be extended with BuildConfig flags or flavor-specific injection to support deployment environments.

---

#### Localized Error Handling

The app does not implement a global error parser. Instead, each ViewModel catches network errors in local `try/catch` blocks and updates its `_error` state accordingly. This keeps feedback localized and predictable.

```kotlin
viewModelScope.launch {
    try {
        val response = RetrofitClient.reviewApi.getAllReviews()
        _reviews.value = response
        _error.value = null
    } catch (e: Exception) {
        _error.value = "Failed to load reviews: ${e.message}"
        Log.e("ReviewViewModel", "Error fetching reviews", e)
    }
}
```

This approach simplifies the flow while still providing users with meaningful feedback.


This integration strategy offers a good balance of modularity and speed. While more advanced concerns like environment configuration, GraphQL, or centralized error transformation are deferred, the current setup fully supports the needs of a clean and maintainable MVP.

### Data Transfer Objects (DTOs)

The Inclusive Trip Planner uses **Data Transfer Objects (DTOs)** to structure all communication between the frontend and the backend. These objects define the shape of the data expected by or returned from the server, ensuring clarity, consistency, and type safety across API boundaries.

---

#### Purpose and Design

DTOs serve as clean containers for backend transport. They are used exclusively for:

- Sending structured data in POST/PUT requests (e.g., `ReviewRequest`, `UserRequest`)
- Receiving typed responses from the backend (e.g., `Review`, `AuthResponse`)

They are not concerned with UI behavior, navigation, or state — their only role is to **encode or decode API contracts**.

This strict separation reduces coupling and allows the frontend to evolve independently from the backend.

---

#### Examples of DTOs

Below are some representative examples of DTOs used in the app:

```kotlin
// Request DTO – for submitting a review
data class ReviewRequest(
    val userId: UUID,
    val itineraryId: UUID,
    val rating: Float,
    val comment: String
)
```

```kotlin
// Response DTO – represents a saved review
data class Review(
    val id: UUID,
    val userId: UUID,
    val itineraryId: UUID,
    val rating: Float,
    val comment: String,
    val createdAt: String,
    val updatedAt: String?
)
```

```kotlin
// Response DTO – returned after successful login
data class AuthResponse(
    val token: String,
    val userId: String,
    val name: String,
    val email: String,
    val phone: String
)
```

This separation ensures that backend-facing logic is kept lightweight and stable, while UI-specific formatting and logic are handled elsewhere.

---

#### DTO Simplicity and Usage

All DTOs are:

- **Flat**: No nested structures unless required by the backend
- **Serializable**: Compatible with Gson for seamless Retrofit conversion
- **Minimal**: Contain only what is needed by the API, no UI helpers or business logic

This makes them easy to debug, test, and refactor, while ensuring clear responsibility boundaries.

UI models (separate from DTOs) are not used at this stage to avoid duplication. However, as feature complexity grows, introducing a mapping layer can help decouple backend formats from presentation logic.

These transformations are minimal and remain inside the ViewModel. No dedicated UI model layer is currently defined.

---

#### Flow of Data in the App

Data flows through DTOs in the following way:

- **From UI to backend**:  
  `ViewModel` builds a request DTO → sends it via `RetrofitClient` → backend receives and processes

- **From backend to UI**:  
  `Retrofit` parses response JSON → converts to response DTO → `ViewModel` emits it via `StateFlow` → UI reacts

ViewModels may transform DTOs into UI-friendly representations if needed, but in most cases, the DTOs are used directly.

---

#### Future Considerations

As the app scales, more advanced patterns may be introduced, such as:

- **Mapping layers** to convert DTOs into rich domain models
- **Separate UI models** to encapsulate display-specific logic
- **Validation helpers** for form DTOs

For the current MVP, however, DTOs remain simple and sufficient.

---

### Navigation and Screen Structure

The Inclusive Trip Planner app uses **Jetpack Compose Navigation** to define screen transitions and handle route parameters. Navigation is declared centrally within `MainActivity`, where a single `NavController` governs the entire application flow. This approach enables clean separation of screens, consistent state management, and easy handling of route arguments.

---

#### Navigation Setup in MainActivity

The main navigation graph is initialized through the `AppNavigator` composable, invoked during the `onCreate()` lifecycle method:

```kotlin
setContent {
    navController = rememberNavController()
    AppNavigator(navController)
}
```

Inside `AppNavigator`, the app defines all available routes using `NavHost`:

```kotlin
NavHost(
    navController = navController,
    startDestination = "welcome"
) {
    composable("home") { HomeScreen(navController, ...) }
    composable("profile") { ProfileScreen(navController) }
    ...
}
```

This graph includes both simple routes (e.g., `"search"`, `"settings"`) and nested flows using `navigation()` blocks (e.g., `"signup_flow"`).

---

#### Parameterized Routes and Navigation Arguments

Some screens require input arguments — for example, viewing itinerary details or writing a review. These arguments are declared via `navArgument` and retrieved from `backStackEntry.arguments`:

```kotlin
composable(
    "itinerary_details/{itineraryId}",
    arguments = listOf(navArgument("itineraryId") { type = NavType.StringType })
) { backStackEntry ->
    val itineraryId = backStackEntry.arguments?.getString("itineraryId") ?: ""
    ItineraryDetailsScreen(navController, itineraryId, ...)
}
```

This allows the app to pass dynamic information (like itinerary IDs) between screens without relying on global state. At this stage, the app does **not** use `SavedStateHandle` or deep links — choices that can be revisited later as the app scales.

---

#### ViewModel Lifecycle and Sharing

ViewModels are scoped appropriately across the app:

- **Global ViewModels**, such as `SavedItinerariesViewModel` or `MyReviewsViewModel`, are initialized using `remember { ... }` in `MainActivity` and passed to multiple screens as needed.
- **Screen-local ViewModels**, such as `ReviewViewModel` or `ItineraryAccessibilityViewModel`, are declared via `viewModel()` inside specific screens.
- In shared cases (e.g., `ItineraryViewModel` used by both Home and Details), the same instance is passed across screens to avoid refetching data unnecessarily.

This hybrid approach keeps memory use efficient while allowing shared access where it makes sense.

---

#### Post-Login Routing and Manual Control

After successful login (e.g., via Google or Facebook), navigation to the home screen is manually triggered from `MainActivity`. For example:

```kotlin
navController.navigate("home") {
    popUpTo("welcome") { inclusive = true }
}
```

This clears the back stack and prevents returning to authentication flows after login. The same technique is used for redirecting users after social login or OTP verification.

This approach also ensures backstack cleanliness — users are not able to navigate back into the login flow after authentication, reinforcing expected navigation behavior.

---

#### Future Considerations

While the current navigation setup fully supports the needs of an MVP, future improvements could include:

- **Deep Linking**: Enabling URL-based access to screens (e.g., opening a review page directly)
- **SavedStateHandle**: Restoring ViewModel state after process death or configuration changes
- **Modular Navigation Graphs**: Splitting routes by feature (e.g., `itineraries_graph`, `profile_graph`) for scalability

---

This structure prioritizes simplicity, clarity, and MVP readiness. Screens remain easy to reason about, ViewModels stay consistent, and navigation remains flexible enough to grow as the app evolves.

# 10 Demonstration of the project

For the demonstration we will use a controlled environment that will let us showcase the capacities and real applications of the Inclusive Trip Planner.

### 10.1 Setup and Execution Environment

The demonstration of the Inclusive Trip Planner prototype was carried out in a controlled local environment that replicates real-world usage conditions. This section summarizes the setup used for the frontend and backend during testing. All technical components and configurations mentioned here have already been described in detail in **Section 8.2**, **Section 8.3**, and **Section 9.1**.

#### Frontend Environment

The mobile application was executed using the Android Emulator in Android Studio. The device emulated was a **Pixel 5 running Android 13 (API level 33)**, matching the setup previously documented. The app was launched directly from within the IDE, using a predefined test user with:

- A valid JWT (generated at login)
- Accessibility preferences (from `user_accessibility_features`)
- Saved itineraries and submitted reviews

All HTTP requests were secured via the `AuthInterceptor`, which appended the `Authorization: Bearer <token>` header. Token handling and API integration were implemented as described in **Section 9.5**.

![Screenshots. Emulator](/documents/images/Emulator.png)

#### Backend Environment

The backend ran as a **Docker containerized Spring Boot application** on the loopback address `http://10.0.2.2:8080`, used by Android emulators to access the host. A second container hosted the PostgreSQL database. The schema and demo data were automatically loaded via **Liquibase changelogs**, as previously explained in **Section 9.1**.

The preloaded dataset included:

- Five curated itineraries with steps and destinations
- Linked accessibility features
- A demo user account with saved trips and reviews

This configuration enabled full feature access during the demonstration, without requiring runtime input. The database was verified before and after testing using DBeaver.

![Screenshots. Docker](/documents/images/Docker.png)

![Screenshots. DBeaver](/documents/images/Dbeaver1.png)


This environment mirrors the intended production deployment structure, offering consistency, reproducibility, and fast iteration.

### 10.2 Demonstration Objectives

The objective of this demonstration is to validate the core functionality of the Inclusive Trip Planner by executing key user flows in a controlled environment. These flows were selected based on their technical relevance and their role in shaping the overall user experience. The demonstration relies on a predefined test user, whose data was inserted into the database in advance, including accessibility preferences, saved itineraries, and previously submitted reviews.

The goals of the demonstration are as follows:

- **Authenticate the user** and verify JWT issuance, storage, and reuse across secured API requests.
- **Fetch available itineraries** from the backend and confirm that data is correctly rendered in the home screen.
- **Display itinerary details**, including title, description, steps, accessibility features, and user reviews.
- **Save and un-save itineraries**, triggering backend updates and reflecting changes in the frontend UI.
- **Validate UI responsiveness**, including loading states, error handling, and dynamic updates during navigation.

These objectives are representative of the most important technical flows in the current implementation. Features such as review posting, accessibility tagging, or user profile updates may also be briefly illustrated if time and space allow, but they are not the primary focus of this thesis demonstration.

### 10.3 Execution Flow and Screens

This section presents a step-by-step walkthrough of the main application screens and usage flows, as experienced by a user interacting with the Inclusive Trip Planner. Each subsection highlights key functionality, backend interaction, and visual elements. The walkthrough is based on the current MVP state, with all features tested and functioning as expected.

---

#### App Launch and Authentication

When the Inclusive Trip Planner app is launched, users are first presented with the **WelcomeScreen**, which serves as the authentication entry point. From this screen, users can log in using their **email address**, **phone number**, or one of the available **social login options** (Google or Facebook).

![Screenshots. WelcomeScreen](/documents/images/WelcomeScreen.png)

##### Login via Email or Phone

Upon selecting an input method:

- If the email or phone number exists in the database, the user is redirected to the **LoginWithPasswordScreen**, where they must enter the correct password associated with their account.

![Screenshots. DBeaver](/documents/images/LoginWithPassword.png)

- If the email or phone number is not found, the user is redirected to the **OTPVerificationScreen**. Although this screen currently acts as a placeholder, it represents the intended flow for verifying new users before registration.

![Screenshots. OTPVerification](/documents/images/OTPVerificationScreen.png)

This logic ensures a consistent and secure login experience. The app dynamically determines whether the user is registered and adapts the flow accordingly. If the user is new, the system initiates the **signup sequence** described in the next subsection.

All authentication-related screens are reactive and connected to the backend for identity validation. The JWT received after successful login is stored securely and reused for subsequent requests, as previously described in **Section 9.5**.

### 10.3.2 Signup Flow

The Inclusive Trip Planner does not use a separate “Sign up” button. Instead, the signup flow is triggered automatically when a user attempts to log in with an email or phone number that does not yet exist in the system. This dynamic entry point streamlines the onboarding process while preserving backend consistency for login flows.

Once an unregistered identifier is entered, the app redirects to a placeholder **OTPVerificationScreen**, which in future deployments will handle real verification logic. From there, the user is guided through a structured series of screens to create their profile and define preferences.

#### SignUpNameScreen

The first step prompts the user to enter their full name and an optional nickname. This data is stored and used across the app for personalization purposes.

![Screenshots. SignUpNameScreen](/documents/images/SignUpNameScreen.png)


#### SignUpEmailScreen / SignUpPhoneScreen

Depending on which contact method was missing during the initial login attempt, the app now collects the complementary identifier (e.g., phone if email was given, or vice versa). This ensures that each user has a complete set of contact information.

![Screenshots. SignUpPhoneScreen](/documents/images/SignUpPhoneScreen.png)

![Screenshots. SignUpEmailScreen](/documents/images/SignUpEmailScreen.png)


#### SignUpPasswordScreen

In this step, the user defines a password, which will be securely hashed before being stored in the backend. The password is later used to authenticate future logins.

![Screenshots. SignUpPasswordScreen](/documents/images/SignUpPasswordScreen.png)

#### SignUpAccessibilityScreen

This screen presents the user with a list of accessibility features retrieved dynamically from the backend. The app fetches all available entries from the `/api/accessibility-features` endpoint and displays them as selectable chips.

Selected features are stored in the `user_accessibility_features` table and will later be used to filter or highlight relevant itineraries.

![Screenshots. SignUpAccessibilityScreen](/documents/images/SignUpAccessibilityScreen.png)

#### SignUpCountriesScreen

In the final step, the user selects the countries they plan to travel to. As with the previous screen, the list of available countries is fetched from the backend. The selections are persisted in the `user_country_access` table, determining which destinations are visible to the user.

![Screenshots. SignUpCountriesScreen](/documents/images/SignUpCountriesScreen.png)

#### Backend Integration

During the signup process, multiple backend calls are made to:

- Fetch available accessibility features and countries
- Persist selected features and country access records
- Finalize the user profile with all submitted information

All steps are integrated with the backend through Retrofit, with proper validation and error handling in place. The data collected during signup directly shapes the personalized experience offered throughout the app.

### 10.3.3 Home Screen and Itinerary Access

After a successful login, users are directed to the **Home Screen**, which serves as the central hub for accessing their saved trips and browsing all available itineraries. This screen plays a crucial role in the user journey, combining personalized information with real-time data loading. From a technical standpoint, it connects to multiple backend endpoints and relies on `ViewModel` state observation to render content dynamically.

#### Next Plan Section

At the top of the Home Screen, the app highlights the user’s **Next Plan**, which is the itinerary they have most recently marked as their upcoming trip. This feature is designed to provide immediate access to the itinerary the user intends to follow next.

The **Next Plan** is retrieved from memory using the `SavedItinerariesViewModel`, and is displayed using the same card component used elsewhere in the app to maintain UI consistency. If no plan has been set, the section falls back to a placeholder message encouraging the user to explore available itineraries.

![Screenshots. HomeScreen](/documents/images/HomeScreen.png)

#### Available Itineraries List

Below the Next Plan section, the app displays a scrollable list of all available itineraries. These are fetched from the backend using the `/api/itineraries` endpoint. The `ItineraryViewModel` retrieves the data asynchronously on screen load, exposes it through `StateFlow`, and Compose automatically recomposes the UI when new data arrives.

Each itinerary card includes:
- Title of the itinerary
- Target destination
- Duration and estimated price
- Average rating
- Icons for relevant accessibility features (if present)

This structure allows users to quickly assess and compare itineraries before choosing one to explore in detail.

![Screenshots. ItineraryList](/documents/images/ItineraryList.png)

#### Technical Notes

All data on this screen is loaded through secure Retrofit calls. The following API endpoints are involved:
- `GET /api/itineraries` – to load all available itineraries
- `GET /api/users/{id}/saved-itineraries` – to retrieve saved trips and determine the Next Plan

Each request includes the JWT stored on the device, added automatically via the `AuthInterceptor`. ViewModels manage both loading and error states to ensure a smooth user experience. For example, if the itinerary list is not yet available, a progress indicator is shown.

#### Optional States and Fallbacks

- If **no Next Plan** is set, the section displays a fallback message and a button linking to the full itinerary list.
- If **no itineraries** are available (e.g., in case of API failure or empty database), the user sees a loading indicator or an empty state message.

![Screenshots. NoNextPlan](/documents/images/NoNextPlan.png)

This screen is a core part of the Inclusive Trip Planner experience and demonstrates successful frontend-backend integration, reactive UI architecture, and token-secured content loading.

### 10.3.4 Itinerary Details

Once an itinerary is selected from the Home Screen, the user is directed to the **ItineraryDetailsScreen**, where full information about the trip is displayed. This screen retrieves and renders data using the itinerary’s unique identifier, passed via navigation.

The screen includes:

- The **itinerary title**, **description**, **duration**, and **price**
- A list of **accessibility features** such as "wheelchair access" or "braille signage"
- A **LazyColumn** displaying **user reviews**, fetched from the backend and sorted by creation date
- Two primary interaction buttons:
  - **Save/Unsave**: Toggles the itinerary’s saved state, calling a backend endpoint to persist the change
  - **Set as Next Plan**: Highlights the itinerary on the Home Screen for quick access

The interface responds dynamically to network and state changes using the associated `ItineraryViewModel`, which manages asynchronous data loading and exposes all content through a reactive `StateFlow`. Visual elements, including cards and icon buttons, are implemented in Jetpack Compose.

At the bottom of the screen, the user can also navigate to write a new review or to access the detailed itinerary steps (see **Section 10.3.5**).

![Screenshots. ItineraryDetails](/documents/images/ItineraryDetails.png)


### 10.3.5 Itinerary Steps

After accessing an itinerary’s details, users can tap a dedicated button to view the sequence of stops that make up the selected trip. This opens the **ItineraryStepsScreen**, which presents both a geographic and descriptive breakdown of the itinerary in a clear, structured layout.

#### Structure and Navigation

The screen is composed of two main sections:

- A **Google Map** displayed at the top, where markers represent the geolocated steps included in the itinerary.
- A **scrollable list** of steps below, each rendered as a clickable card showing the title and a description of the step.

Tapping on a step card highlights its location on the map and expands the card to reveal additional information, such as guidance or historical context. Only one step is active at a time, allowing the user to focus on their current position or interest.

![Screenshots. ItinerarySteps](/documents/images/ItinerarySteps.png)

#### Data Handling and Integration

All steps are retrieved from the backend based on the itinerary’s unique ID. This process is handled by the `ItineraryStepViewModel`, which performs a secured Retrofit call to fetch step data and exposes it via `StateFlow`. The UI observes this state and recomposes accordingly, ensuring smooth updates and user feedback.

Geographic markers are built using the step coordinates (latitude and longitude) provided by the backend. The `MapScreen` composable integrates with Google Maps and responds to step selection by adjusting marker styles and focus.

![Screenshots. SelectedStep](/documents/images/SelectedStep.png)

#### Implementation Notes

- Steps are stored in the `itinerary_steps` table and linked to itineraries via foreign keys.
- The UI is fully built with Jetpack Compose and uses `LazyColumn` for efficient rendering.
- The selected step is tracked in local state, allowing the UI and map to stay in sync.
- If the itinerary has no steps, the screen displays a fallback message instead of rendering empty content.

This screen reinforces the structure and clarity of the travel experience, especially for users relying on precise location-based cues.

### 10.3.6 Review Submission

The Inclusive Trip Planner allows users to share their feedback on each itinerary by submitting a review. These reviews help future users evaluate trip quality and accessibility, while also serving as a form of engagement and content enrichment. The review system supports both textual and star-based input, with the option to post anonymously.

#### Access Points

Users can access the review form from two locations:

- The **ItineraryDetailsScreen**, which features a clearly visible “Write a Review” button.
- The **ProfileScreen**, where users can revisit their activity and initiate reviews for previously viewed itineraries.

![Screenshots. ItineraryReviewButton](/documents/images/WriteAReviewButton.png)

#### WriteAReviewScreen

Tapping the review button navigates the user to the **WriteAReviewScreen**, which presents a clean form composed of the following fields:

- **Rating (1–5 stars)**: selected via a visual star component.
- **Comment (optional)**: up to 255 characters.
- **Nickname (optional)**: shown publicly if anonymity is disabled.
- **Anonymity toggle**: if enabled, hides the user’s identity from the review.

The form includes basic validation (e.g., rating required) and becomes submittable only when the criteria are met. Upon submission, the app builds a `ReviewRequest` object and forwards it to the backend via the `ReviewViewModel`.

![Screenshots. WriteAReview](/documents/images/WriteAReview.png)

#### Backend Integration

Once validated, the review is posted to the `/api/reviews` endpoint. The payload includes:

- `userId`
- `itineraryId`
- `rating`
- `comment`
- `anonymous` flag

The backend processes the request and stores the review in the database. If successful, the newly created review is returned and immediately appended to the current list of itinerary reviews on the frontend.

![Screenshots. ReviewDBeaver](/documents/images/ReviewDBeaver1.png)


#### Review Display

All reviews for a given itinerary are rendered on the **ItineraryDetailsScreen** using the `ReviewCard` composable. This component visually displays:

- The star rating
- The comment
- The nickname or “Anonymous” label
- The creation date

Reviews are displayed in a `LazyColumn`, sorted by recency, and fetched when the itinerary details are loaded. If no reviews are available, the UI shows a placeholder encouraging users to be the first to review.

![Screenshots. ItineraryDetails](/documents/images/ItineraryDetails2.png)

#### Technical Notes

The entire review flow is managed by the `ReviewViewModel`, which handles:

- Backend communication via Retrofit
- Local state updates via `MutableStateFlow`
- Error handling via a separate `_error` state
- Token-based authentication through the `AuthInterceptor`

Any failed requests result in user-facing error messages. On success, the review appears immediately without requiring a full screen reload.

This feature completes the feedback loop in the itinerary flow and provides meaningful context for future users evaluating a trip.

### 10.3.7 Saved Itineraries

In addition to browsing and reviewing itineraries, users of the Inclusive Trip Planner can save specific trips for later reference. This bookmarking functionality adds flexibility to the planning process by allowing users to maintain a personalized list of trips they are interested in, independently of their current navigation session.

#### Saving and Removing Itineraries

Users can save or un-save itineraries directly from the **ItineraryDetailsScreen** by tapping the save icon located near the top of the screen. The button toggles between two visual states to indicate whether the itinerary is currently bookmarked.

These interactions trigger secure API requests to the backend and update the local state in real time. The response is immediately reflected in the UI, ensuring a smooth and responsive experience.

![Screenshots. SaveButton](/documents/images/SaveButton.png)

#### SavedItinerariesScreen

All bookmarked itineraries are displayed in the **SavedItinerariesScreen**, which serves as a personal archive. This screen uses a vertical scrollable list of cards, each presenting key information about the saved trip, such as:

- Title and destination
- Accessibility features
- Duration, rating, and price
- Visual thumbnail if available

![Screenshots. SavedItineraries](/documents/images/SavedItinerariesScreen.png)

This screen is built using Jetpack Compose and is backed by the `SavedItinerariesViewModel`, which handles state updates and synchronization with the backend.

#### Backend Integration

The backend supports full CRUD functionality for saved itineraries. The following endpoints are involved in this flow:

- `POST /api/saved-itineraries`  
  To create a new save entry given a `userId` and `itineraryId`.
- `DELETE /api/saved-itineraries/{id}`  
  To remove an existing saved itinerary.
- `GET /api/users/{userId}/saved-itineraries`  
  To retrieve the list of all itineraries currently saved by the user.

Each saved record is stored in the `saved_itineraries` table, with a unique constraint on the `user_id` and `itinerary_id` pair to avoid duplicates.

#### Fallback and Error Handling

If the user has no saved itineraries, the screen displays a placeholder message suggesting that they explore the available trips in the Home or Search screen. Loading indicators and error messages are also managed through the associated ViewModel to provide clear feedback in case of connectivity issues.

>![Screenshots. SavedItinirerariesMissing](/documents/images/SavedItinerariesEmpty.png)

This feature reinforces user control over their planning process and supports asynchronous exploration, allowing itineraries to be marked and revisited across sessions.

### 10.3.9 Profile Screen

The **ProfileScreen** provides a centralized view of the user's identity, preferences, and activity within the Inclusive Trip Planner. It consolidates personal information, accessibility features, and navigation shortcuts, offering both an overview and a starting point for deeper interaction.

#### Layout and Structure

The screen is composed of the following key elements:

- **Header Section**: Displays the user’s name and a circular avatar placeholder.
- **Account Information Card**:
  - Displays the registered email and phone number.
  - Lists the user's selected accessibility preferences using a read-only chip grid.
- **Activity Section**:
  - Allows quick access to the user’s submitted reviews.
  - Additional actions (e.g. bookmarks or trip history) can be added in future updates.

![Screenshots. Profile Screen](/documents/images/ProfileScreen.png)

At the top-right, users can navigate to **Settings** or **Notifications**, both accessible via icon buttons in the custom top bar. These screens are not the primary focus of this demonstration but are fully implemented and accessible.

#### Backend Integration

All displayed data is loaded from the backend using the JWT stored after login:

- The `UserViewModel` fetches the user’s account data.
- The `AccessibilityFeatureViewModel` loads the features linked to the user ID.

This ensures the content remains consistent with the user's profile as stored in the database.

The screen is fully scrollable and responsive, ensuring a smooth user experience even on smaller devices. The design reinforces clarity and consistency, aligning with the accessibility-first principles of the app.

### 10.4 Visual Feedback and Responsiveness

This section illustrates how the Inclusive Trip Planner communicates state changes, errors, and successful interactions to the user. Rather than focusing on underlying implementation details—already covered in Section 9—this part showcases the actual **visual behavior** experienced during the demonstration. The aim is to highlight how the app provides clarity, confidence, and smooth interaction through loading indicators, fallback states, and responsive UI updates.

#### Loading Indicators and Progress Feedback

To ensure that users are informed when data is being fetched, the application consistently displays visual loading cues. These are most visible in:

- The **HomeScreen**, where itineraries are fetched asynchronously on screen load.
- The **SignUpAccessibilityScreen** and **SignUpCountriesScreen**, which retrieve lists from the backend before rendering chips.
- The **Review submission flow**, where the submit button is temporarily disabled, and a progress spinner may appear.

These indicators prevent user confusion and reinforce the perception of a responsive system.

#### Error Messaging and Fallback States

During testing, several error-handling flows were intentionally triggered to verify visual feedback. The most notable examples include:

- Invalid login attempts, which return a clear, localized error message near the input field.
- Failed itinerary or review fetches, which trigger a visible error message or an empty state with suggested actions.
- Absence of saved itineraries, which prompts a friendly placeholder message encouraging users to explore available trips.

These fallback states are simple but effective, and help prevent dead ends or unexplained blank screens.

#### Dynamic UI Behavior

The interface responds immediately to user actions, thanks to reactive ViewModel-based state management. Key examples observed during the demonstration include:

- The **save/unsave button** on the itinerary details screen toggles its icon instantly after being tapped.
- The **“Next Plan” section** in the HomeScreen updates live after a user marks a new trip.
- When a review is submitted, it appears **instantly** at the top of the review list without a full reload.

This behavior reinforces a feeling of real-time interactivity and technical reliability.

#### UI Layout and Spacing Improvements

Compared to earlier versions, the current interface has been refined to improve readability and usability:

- **Chips for accessibility and countries** now use padding and flow layouts to reduce visual clutter.
- Cards in the HomeScreen and SavedItinerariesScreen maintain consistent margins and spacing, offering a more breathable layout.
- Scrollable areas, such as itinerary steps and reviews, maintain smooth performance even with multiple items.

These improvements were made iteratively during development and directly enhance the user experience.

![Screenshots. Chips](/documents/images/OldChips.png)

![Screenshots. Chips](/documents/images/NewChips.png)
#### Accessibility Considerations

Special attention was given to the **readability and spacing** of interface elements related to accessibility. In the onboarding flow, each chip has a large enough touch target, with high-contrast labels and intuitive toggling behavior. Even when users select many options, the layout remains functional, though it can become visually dense with excessive selections.

Accessibility indicators on itinerary cards and detail screens also follow clear iconography, supporting at-a-glance interpretation.

![Screenshots. HighContrast](/documents/images/HighContrast.png)

#### Conclusion

The Inclusive Trip Planner MVP demonstrates a consistent and user-friendly approach to visual feedback. From loading indicators to dynamic UI updates and fallback states, each aspect of the interface contributes to a smooth and predictable experience. These qualities, while subtle, are essential to the app’s overall accessibility and polish—even at this early stage of development.

### 10.5.1 Database State Verification

To ensure that frontend interactions were correctly persisted, each user-triggered action during the demonstration was verified directly against the backend database using DBeaver. This process served to confirm that operations such as saving itineraries and submitting reviews resulted in real, observable changes in the system’s state.

#### Saved Itinerary Entry

After the user saved an itinerary from the **ItineraryDetailsScreen**, a corresponding entry was created in the `saved_itineraries` table. Each record links a `user_id` and an `itinerary_id`, with a unique constraint to prevent duplicates. The row also includes timestamps for creation and update.

![Screenshots. SavedItinerariesDBeaver](/documents/images/SavedItineraryDBeaver.png)

This confirms that the save operation was successfully propagated through the ViewModel, Retrofit API, controller, service, and repository layers.

#### Submitted Review Entry

When a review was submitted from the **WriteAReviewScreen**, a new entry appeared in the `reviews` table. The inserted row included:

- The correct `user_id` and `itinerary_id`
- The numerical `rating` provided by the user
- An optional `comment`
- The `anonymous` flag as selected in the form
- Timestamps for creation and update

This step validated not only the correct functioning of the review submission flow, but also the integrity of the persisted data, such as the anonymity logic and timestamp accuracy.

Both of these checks confirmed that frontend actions were properly stored and persisted in the backend, using actual database queries rather than relying solely on frontend state or API responses.

### 10.5.2 API Activity and Logging

During the demonstration, backend API activity was continuously monitored to validate request execution and ensure that all user actions triggered the appropriate server-side behavior. Two sources of logging were used for this purpose: the **Android Logcat** and the **Spring Boot console output**.

#### Android Logcat Monitoring

The Android Studio Logcat console captured all outgoing HTTP requests initiated by Retrofit, including:

- Request methods (e.g., `POST`, `GET`, `DELETE`)
- Target endpoints (e.g., `/api/saved-itineraries`)
- Attached headers, such as `Authorization: Bearer <token>`

This trace was used to verify that tokens were correctly injected and that all requests were routed as expected. Logcat also reported error cases, such as failed authentication or unreachable backend, allowing for quick debugging during the session.


#### Spring Boot Console Output

On the backend side, the Spring Boot application produced real-time logs for every incoming request. These logs included:

- The HTTP method and route accessed
- Status codes returned (e.g., `200 OK`, `201 Created`, `401 Unauthorized`)
- Any validation failures or exceptions thrown
- Timestamps and controller mappings

These logs confirmed that requests were successfully received and processed by the correct controllers and services. For example, during review submission, the POST request to `/api/reviews` was logged with the full payload, and successful storage was confirmed with a `201 Created` response.


#### JWT Validation and Error Feedback

In cases where requests lacked a valid JWT or used an expired token, the backend correctly returned a `401 Unauthorized` error. These cases were captured in both Logcat and the Spring Boot logs, and visually confirmed in the app through user-facing error messages (e.g., toast or snackbar alerts).

This multi-level monitoring provided a complete picture of API behavior and ensured that all frontend actions were effectively translated into backend operations, with clear feedback in both success and failure cases.

### 10.5.3 Error Feedback Confirmation

To verify the robustness and transparency of the Inclusive Trip Planner, a set of intentional backend failures were triggered during the demonstration. These tests aimed to ensure that the system gracefully handled error conditions and provided informative feedback to both developers and end users.

#### Invalid Token Handling

A deliberately malformed or expired JWT was used when making a secured request (e.g., fetching saved itineraries). As expected:

- The backend returned a `401 Unauthorized` response.
- The frontend captured the error via the `AuthInterceptor` and displayed a toast/snackbar message indicating authentication failure.
- No crash or inconsistent state occurred.

This confirmed that JWT validation was working correctly and that unauthorized access was properly blocked.

#### Duplicate Save Request

An itinerary was saved twice in rapid succession. The backend contains a unique constraint on `(user_id, itinerary_id)` in the `saved_itineraries` table, so the second request triggered a database constraint violation.

- The backend returned an appropriate error (typically `400 Bad Request` or `409 Conflict`).
- The frontend handled this gracefully by ignoring redundant actions and maintaining the saved state in the UI.

This demonstrated correct idempotency and error handling in save operations.

#### Missing Fields in Review Submission

To test input validation, a review submission was attempted with a missing rating field — which is required by the backend.

- The backend rejected the request with a `400 Bad Request` response.
- The response body included a meaningful error message, confirming that validation rules were enforced.
- The frontend caught the error, displayed a localized error message, and kept the user on the same screen for correction.

These scenarios verified that validation logic, error propagation, and frontend messaging were all functioning as intended. The application remained responsive and stable throughout, with no unexpected behavior during any of the induced failure states.

### 10.5.4 Summary of API Observability

The demonstration of the Inclusive Trip Planner emphasizes not only the visual behavior of the application, but also the traceability of each core interaction. Every user action—whether submitting a review, saving an itinerary, or logging in—triggers a corresponding backend event, making the relationship between the frontend and backend both **transparent and verifiable**.

#### Observable Layers

The system provides observability across three distinct layers:

- **Frontend logs** (Logcat):  
  - All Retrofit requests and errors are printed in the Android Studio console.
  - Token-related failures, submission errors, and success states are easily traced during runtime.

- **Backend logs** (Spring Boot output):  
  - Each API route hit is logged along with HTTP method and response status.
  - Errors such as validation failures (`400`), unauthorized access (`401`), and constraint violations (`409`) are clearly reported.

- **Database changes** (PostgreSQL via DBeaver):  
  - Every action that modifies data—such as saving an itinerary or submitting a review—can be immediately confirmed by inspecting the corresponding tables.
  - No user interaction is "faked" or hardcoded; all mutations are persistent and reflect actual application behavior.


This level of transparency confirms that the prototype operates as a fully functional MVP rather than a scripted or simulated demo. Each step in the demonstration produced measurable backend effects, strengthening confidence in the system’s architecture, integration, and correctness.

### 10.6 Summary and Next Steps

This final section of the demonstration chapter offers a concise reflection on the results presented and outlines the next technical objectives for the Inclusive Trip Planner prototype. The demonstration successfully confirmed that the system performs all intended user flows under realistic conditions, and that every core feature—authentication, itinerary exploration, review submission, and trip saving—produces verifiable backend effects.

From both a user and developer perspective, the flows executed during the demo were consistent, responsive, and integrated. Each action triggered the appropriate backend endpoint, returned feedback to the user through a dynamic UI, and produced measurable results in the database. The logs collected during testing further validated that these behaviors were not simulated or hardcoded, but stemmed from real-time communication between the frontend and backend components.

In its current state, the application fulfills the criteria of a working MVP. It allows users to explore curated, accessibility-tagged itineraries, save favorites, review trips, and manage their profile information. The system also enforces security through JWT-based authentication and manages API access with clear backend validations.

Several enhancements are already planned for the next development phase, including:

- **Dynamic itinerary filtering**, based on user-selected accessibility preferences.
- **Persistent next-plan designation**, stored beyond in-memory runtime.
- **Full deployment pipeline**, replacing the local environment with a staging or production-ready instance.

These upcoming improvements build directly on the foundation demonstrated in this chapter. Overall, the prototype has met its primary goals of feasibility validation, accessibility-first design, and solid architectural integration, confirming the viability of the project as it moves toward future releases.

# Conclusions

## 11.1 Summary of Results

The Inclusive Trip Planner successfully meets the core objectives defined at the outset of the project. From a technical perspective, the prototype demonstrates a fully functional MVP capable of supporting key user flows such as login, itinerary browsing, detail viewing, saving, and reviewing. Each of these features is backed by secure backend logic, real-time frontend feedback, and persistent data storage in a PostgreSQL database.

Unlike a simulated or scripted demo, the demonstration conducted in Section 10 was grounded in real-time interactions between the frontend and backend. Each user action—whether saving an itinerary or submitting a review—resulted in observable API activity, state changes, and database mutations. These interactions were verified across multiple levels, including log outputs, database inspection, and visual UI updates.

The system architecture—composed of a Kotlin-based Android frontend and a Spring Boot backend—proved to be stable, maintainable, and aligned with best practices. The frontend used reactive ViewModels and Retrofit for communication, while the backend followed a layered structure that separated models, DTOs, services, and controllers.

Overall, the prototype confirms the technical feasibility of an accessible trip-planning application and lays a strong foundation for future iterations, including advanced personalization and large-scale deployment.

### 11.2 Implications of the Results

The results of this project highlight both the need and the potential for inclusive design in digital travel applications. By placing accessibility at the core of the experience—rather than treating it as an afterthought—the Inclusive Trip Planner prototype demonstrates that personalization and equity can be embedded into every step of the user journey.

From a technical standpoint, the successful integration of features such as accessibility-aware onboarding, itinerary tagging, and anonymous feedback suggests that inclusive systems can be built using standard development tools and frameworks. These capabilities do not require exotic infrastructure; instead, they rely on thoughtful schema design, flexible APIs, and a commitment to user-centered workflows.

Furthermore, this thesis illustrates that accessibility-driven logic can be incrementally introduced into existing applications. Even though the current version does not yet support dynamic filtering or live itinerary adaptation, the architecture anticipates these features and has been designed to support them in future releases.

In a broader sense, this project affirms that inclusive design is not only technically achievable—it is also a meaningful differentiator. Travel applications that consider diverse needs are better equipped to serve wider audiences, including elderly users, people with visual or motor impairments, and travelers with language or navigation challenges.

By validating these ideas through a working prototype, this thesis contributes to the growing recognition that accessibility is not a constraint, but an opportunity to improve usability, engagement, and societal impact in mobile application development.

### 11.3 Limitations

While the Inclusive Trip Planner demonstrates a fully functional and testable MVP, several limitations remain in both scope and implementation that should be acknowledged for a balanced evaluation.

#### Static Itinerary Content

The most notable limitation is the lack of dynamic itinerary generation. All itineraries are currently **predefined and hardcoded** in the database, meaning that the application does not yet offer personalized trip suggestions based on user preferences or constraints. Although accessibility filters were collected during signup, they are not yet used to filter or prioritize itineraries in the frontend.

#### Limited Accessibility Logic

While the app collects and stores accessibility features and links them to itineraries, the **logic for filtering or adapting content** based on these features is not implemented in the current version. For example, users with motor impairments are not yet offered tailored navigation steps or filtered activity lists. This results in a gap between data collection and actionable user experience.

#### Authentication Flow Gaps

Although login via email, password, and Google/Facebook are implemented and functional, the **OTP verification flow remains a placeholder**. This means that phone-only signups cannot yet be completed without manual backend entry or test data preparation. This limitation affects the onboarding of first-time users and will need to be addressed in a production deployment.

#### No Offline or Cache Mode

The current app requires a **continuous internet connection** to function properly. It does not cache itineraries, reviews, or user data for offline use, which limits its utility in real-world travel scenarios where connectivity may be limited or intermittent.

#### MVP Constraints

By design, the system reflects a **minimum viable product** focused on technical integration and core flows. Features such as itinerary search, user notifications, settings customization, or real-time itinerary updates are either stubbed or excluded from this version. These omissions are not oversights but conscious trade-offs to prioritize stability and technical clarity for demonstration purposes.

---

Despite these limitations, the core workflows—authentication, itinerary viewing, saving, and reviewing—are complete, functional, and consistent. They lay a strong foundation for future iterations that could introduce the missing features without significant architectural changes.

### 11.4 Future Work

The development of the Inclusive Trip Planner MVP has laid a strong foundation for a personalized, accessibility-focused travel experience. Based on the current implementation and its known limitations, several clear avenues for future work have been identified to enhance functionality, scalability, and inclusivity.

#### 1. Personalized Itinerary Recommendations

One of the most impactful future improvements is the integration of **personalized itinerary filtering** based on users' selected accessibility features. This would involve:

- Updating backend queries to prioritize itineraries matching user needs.
- Introducing filters in the frontend to adjust the list dynamically.
- Designing new screens or components that visually indicate suitability for the user.

This personalization logic is already anticipated in the data model, requiring only logic implementation and UI enhancements to activate.

#### 2. OTP Verification and Full Phone Signup Support

To complete the user onboarding flow, the **OTPVerificationScreen** must be connected to an actual SMS verification backend (e.g., Firebase or Twilio). This would allow:

- Secure account creation via phone number alone.
- Better onboarding for users without email access.
- Multi-device support via phone-based login.

This improvement would close one of the last gaps in the authentication process.

#### 3. Offline Access and Smart Caching

Given the nature of travel, the app should provide **offline capabilities** such as:

- Locally caching saved itineraries and itinerary steps.
- Allowing users to view trip details without internet access.
- Storing failed actions (e.g. review submission) to retry once reconnected.

This would greatly increase the reliability and practicality of the application for real-world use.

#### 4. Accessibility-Specific Enhancements

In addition to basic accessibility metadata, future versions of the app could introduce:

- **Voice guidance** for users with visual impairments.
- **Haptic feedback** or custom gesture support for motor impairments.
- Improved **screen reader compatibility** and larger font settings.

Such features would extend the app’s usability to a broader audience and align it with WCAG recommendations.

#### 5. Admin Interface and Content Expansion

To scale itinerary management, an **admin dashboard** could be introduced to:

- Create and edit itineraries directly via a web UI.
- Moderate user reviews and content submissions.
- Add accessibility data or flag inappropriate content.

Additionally, expanding the dataset to support multiple countries, cities, and languages would allow the app to serve a truly global audience.

#### 6. Deployment and User Testing

While the current system runs locally, production readiness would involve:

- Deploying the backend to a cloud provider with HTTPS.
- Publishing the app to the Play Store or TestFlight.
- Conducting structured **user testing sessions** to gather usability feedback.

These steps would transform the MVP into a usable public release, with continuous iteration based on real-world needs.

---

Future work will focus on transforming the Inclusive Trip Planner from a strong proof-of-concept into a scalable, user-centric platform capable of supporting a wide range of travelers and accessibility needs.

### 11.5 Final Words

The Inclusive Trip Planner project has been an opportunity to explore the intersection between travel, accessibility, and modern mobile development. From its initial conception to the delivery of a fully functioning MVP, the project has evolved into a tangible demonstration of how technology can empower underrepresented users in the travel space.

Through the implementation of authentication, accessibility preference management, itinerary navigation, review submission, and backend integration, the app demonstrates not only its feasibility, but also its potential for real-world impact. Each decision—from the layered architecture of the backend to the reactive frontend design—was made with both scalability and user inclusiveness in mind.

While this version is still an early prototype, it is technically solid and offers a clear roadmap for future improvements. More importantly, it highlights the value of inclusive design—not as an afterthought, but as a core principle guiding both functionality and user experience.

This thesis marks the end of the current development cycle, but not the end of the journey. With the foundational work now complete, the next steps involve scaling, testing, and iterating based on real feedback. The Inclusive Trip Planner is not just a school project—it is a launchpad for something bigger.

## Appendix

### [1] Jetpack Compose
A modern Android UI toolkit developed by Google for building native interfaces using a declarative approach.

### [2] Spring Boot
A Java-based framework for building microservices and backend applications quickly, with minimal configuration.

### [3] PostgreSQL
An open-source object-relational database system known for reliability and SQL compliance.

### [4] DTO (Data Transfer Object)
An object used to transfer data between software application layers, commonly used to decouple models from the API.

### [5] Accessibility
The design of products, devices, services, or environments so they can be used by people with disabilities.

### [6] WCAG (Web Content Accessibility Guidelines)
A set of international standards developed by the W3C to make digital content more accessible to people with disabilities.

### [7] Firebase Authentication
A secure identity platform provided by Google that enables login using email, Google, Facebook, and other providers, commonly used in mobile apps.

### [8] Liquibase
An open-source tool for tracking, versioning, and applying database schema changes. It allows defining changesets in XML, YAML, JSON, or SQL.

### [9] Retrofit
A type-safe HTTP client for Android and Kotlin that simplifies communication with RESTful APIs. It converts HTTP responses into Kotlin/Java objects using annotations.

### [10] ViewModel
A component of Android’s architecture that stores and manages UI-related data in a lifecycle-conscious way. It helps decouple UI logic from activity/fragment classes.

### [11] StateFlow
A state-holder observable from Kotlin’s coroutines library. It’s lifecycle-aware and emits UI state updates in a reactive way, often used with `ViewModel`.

### [12] TokenManager

A Kotlin utility class responsible for securely storing and retrieving JWT tokens across app sessions, typically backed by Android’s DataStore or SharedPreferences.

### [13] AuthInterceptor

An OkHttp interceptor that attaches the stored JWT to the `Authorization` header of outgoing HTTP requests, enabling secure communication with the backend.