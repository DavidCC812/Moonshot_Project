# Functional Specification

<details>
<summary>Table of Contents</summary>

- [Functional Specification](#functional-specification)
  - [1. Introduction](#1-introduction)
    - [1.1 Purpose](#11-purpose)
    - [1.2 Project Overview](#12-project-overview)
    - [1.3 Objectives](#13-objectives)
    - [1.4 Intended Audience](#14-intended-audience)
    - [1.5 Scope of the Document](#15-scope-of-the-document)
  - [2. Stakeholders](#2-stakeholders)
    - [2.1 Key Stakeholders](#21-key-stakeholders)
    - [2.2 Stakeholder Expectations](#22-stakeholder-expectations)
  - [3. Personas](#3-personas)
    - [3.1 Persona 1: Sarah Thompson](#31-persona-1-sarah-thompson)
    - [3.2 Persona 2: Liam Patel](#32-persona-2-liam-patel)
    - [3.3 Persona 3: Maria Gonzalez](#33-persona-3-maria-gonzalez)
    - [3.4 Persona 4: Alex Johnson](#34-persona-4-alex-johnson)
  - [4. Scope](#4-scope)
    - [4.1 Features](#41-features)
      - [Accessibility Ratings](#accessibility-ratings)
      - [Travel Planning Tools](#travel-planning-tools)
      - [Community Support](#community-support)
    - [4.2 Exclusions](#42-exclusions)
    - [4.3 Limitations](#43-limitations)
  - [5. Functional Requirements](#5-functional-requirements)
    - [5.1 Core Features](#51-core-features)
      - [5.1.1 Accessibility Ratings](#511-accessibility-ratings)
      - [5.1.2 Travel Planning Tools](#512-travel-planning-tools)
      - [5.1.3 Community Support](#513-community-support)
    - [5.2 Additional Functionalities](#52-additional-functionalities)
      - [5.2.1 User Profiles](#521-user-profiles)
      - [5.2.2 Notifications](#522-notifications)
      - [5.2.3 Accessibility Database](#523-accessibility-database)
    - [5.3 System Management](#53-system-management)
      - [5.3.1 Content Moderation](#531-content-moderation)
      - [5.3.2 Data Backup](#532-data-backup)
      - [5.3.3 Administrator Platform Overview](#533-administrator-platform-overview)
  - [6. User Journeys / Use Cases](#6-user-journeys--use-cases)
    - [6.1 Use Case: Submitting an Accessibility Rating](#61-use-case-submitting-an-accessibility-rating)
    - [6.2 Use Case: Creating a Custom Itinerary](#62-use-case-creating-a-custom-itinerary)
    - [6.3 Use Case: Participating in Community Forums](#63-use-case-participating-in-community-forums)
    - [Granular User Journey Analysis (Future Figma Integration Placeholder)](#granular-user-journey-analysis-future-figma-integration-placeholder)
  - [7. Non-Functional Requirements](#7-non-functional-requirements)
    - [7.1 Performance](#71-performance)
    - [7.2 Scalability](#72-scalability)
    - [7.3 Accessibility Standards](#73-accessibility-standards)
    - [7.4 Security](#74-security)
      - [Privacy](#privacy)
    - [7.5 Reliability](#75-reliability)
    - [7.6 Usability](#76-usability)
    - [7.7 Maintainability](#77-maintainability)
  - [8. Preliminary Design](#8-preliminary-design)
    - [8.1 UI Concepts](#81-ui-concepts)
    - [8.2 Navigation Flow](#82-navigation-flow)
    - [8.3 Technology Stack Visualization](#83-technology-stack-visualization)
  - [9. Glossary](#9-glossary)

</details>

## 1. Introduction

### 1.1 Purpose

The **Inclusive Trip Planner** is a mobile application designed to cater to travelers with disabilities or specific needs by providing accessible and inclusive travel planning tools. The goal is to bridge the gap in the travel industry by offering features such as accessibility ratings, travel planning customization, and community-driven support. This document outlines the functional specifications for the application.

### 1.2 Project Overview

The Inclusive Trip Planner addresses the challenges faced by individuals requiring accessible travel options. By leveraging user-generated content and streamlined planning tools, the application creates an inclusive environment where users can plan their journeys in their respective cities (Paris for now) confidently and efficiently. The app also encourages community engagement by fostering a platform where users can share accessibility-related information and experiences.

### 1.3 Objectives

- **Inclusivity**: Empower individuals with disabilities to explore their city with ease.
- **Community Support**: Build a platform where users can contribute and benefit from shared accessibility knowledge.
- **Accessibility Awareness**: Promote a culture of inclusivity within the travel industry.

### 1.4 Intended Audience

- **Primary Users (Travelers)**: Individuals with disabilities or specific accessibility needs.
- **Secondary Users (Contributors)**: Accessibility advocates and travelers interested in enhancing the platform’s offerings through contributions and reviews.

### 1.5 Scope of the Document

This document focuses on the functional requirements and user flows of the Inclusive Trip Planner. Non-functional requirements such as performance and scalability will also be discussed to ensure the app meets quality standards. Technical implementation details and project reporting are outside the scope of this document.

![SystemContext](/documents/images/SystemContext.png)

---

## 2. Stakeholders

### 2.1 Key Stakeholders

- **Project Owner:** David Cuahonte Cuevas, responsible for the development and strategic direction of the Inclusive Trip Planner.
- **Developer:** David Cuahonte Cuevas Tasked with implementing the features and ensuring the application meets both functional and non-functional requirements.
- **Primary Users (Travelers):** Individuals utilizing the app to plan trips tailored to their specific accessibility needs.
- **Secondary Users (Contributors):** Community members providing reviews, ratings, and information about accessibility features for various destinations.

### 2.2 Stakeholder Expectations

- **Project Owner Expectations:**

  - A clear and feasible roadmap for development.
  - Alignment with project goals and target audience needs.

- **Developer Expectations:**

  - Comprehensive and clear requirements to guide implementation.
  - Access to necessary tools and resources for development.

- **Primary User Expectations:**

  - A user-friendly interface with accurate and relevant information.
  - Reliable tools for planning and navigating accessible trips.

- **Secondary User Expectations:**
  - A platform that values and integrates their contributions seamlessly.
  - Recognition for their input in improving accessibility awareness.

![Stakeholders](/documents/images/Stakeholders.png)

---

## 3. Personas

### 3.1 Persona 1: Sarah Thompson

- **Description**: Sarah is a mobility-impaired traveler who relies on assistive devices and prefers destinations that cater to wheelchair accessibility. She enjoys traveling but faces challenges when finding reliable accessibility information for hotels and attractions.
- **Needs & Goals**: Sarah needs an app that provides detailed accessibility ratings and recommendations tailored to her needs. Her primary goal is to plan worry-free trips that ensure access and convenience.
- **Frustrations**: She is frustrated by the lack of centralized, accurate data on accessibility and often spends excessive time cross-referencing information from multiple sources.

![Personas](/documents/images/PersonaSarah.png)

### 3.2 Persona 2: Liam Patel

- **Description**: Liam is visually impaired and enjoys exploring new cities with friends. He values community input and relies heavily on clear, audio-guided navigation and community-driven tips.
- **Needs & Goals**: Liam needs an app that supports audio accessibility features and provides clear, step-by-step navigation to avoid inaccessible routes. He also values community reviews for specific insights.
- **Frustrations**: Liam finds many travel apps lack robust accessibility options and rarely cater to visually impaired users, making his planning process frustrating and incomplete.

![Personas](/documents/images/PersonaLiam.png)

//Uncertain of this part as im not too sure on what handicaps we will focus for now

### 3.3 Persona 3: Maria Gonzalez

- **Description**: Maria is a caregiver for her elderly parents who require mobility assistance. She frequently plans family trips and seeks reliable, user-friendly tools to help her find accessible options for dining, accommodations, and activities.
- **Needs & Goals**: Maria needs an app that allows her to filter results by accessibility features such as elevators, wheelchair ramps, and handrails. She prioritizes efficiency in planning and trustworthy recommendations.
- **Frustrations**: Maria often struggles with unclear or incomplete accessibility information, leading to last-minute changes to her travel plans.

![Personas](/documents/images/PersonaMaria.png)

### 3.4 Persona 4: Alex Johnson

- **Description**: Alex is a wheelchair user who actively participates in online communities to share their experiences about accessible destinations. They enjoy helping others by providing honest and detailed reviews about accessibility features in various locations.
- **Needs & Goals**: Alex needs a platform that allows them to easily submit reviews and ratings about accessibility features. Their primary goal is to contribute to a trustworthy database that benefits other travelers with similar needs.
- **Frustrations**: Alex finds existing platforms often lack structured formats for accessibility reviews, making it challenging to provide consistent and useful feedback. They are also frustrated by the lack of acknowledgment or incentives for their contributions.

![Personas](/documents/images/PersonaAlex.png)

## 4. Scope

### 4.1 Features

#### Accessibility Ratings

- Allow users to rate and review destinations, including restaurants, hotels, and attractions, based on accessibility features (e.g., wheelchair access, hearing aid support).
- Enable filters for specific accessibility requirements such as ramp availability or braille signage.

#### Travel Planning Tools

- Provide customizable itineraries that integrate accessibility data.
- Offer map integration with optimized routes that avoid inaccessible paths or areas.
- Allow users to save favorite locations for quick access during trip planning.

#### Community Support

- Include forums or message boards where users can share experiences and tips.
- Enable peer-to-peer recommendations for accessible travel experiences.
- Create a rewards system for active contributors to incentivize participation.

### 4.2 Exclusions

- **Dietary Filters**: Features to locate dining options catering to specific dietary restrictions (e.g., vegan, kosher, halal) will not be implemented due to time constraints.
- **Integrated Booking**: The ability to book accommodations, restaurants, or attractions directly through the app is excluded from this version.

### 4.3 Limitations

- Accessibility data relies heavily on user contributions, which may lead to inconsistencies in coverage and accuracy.
- The app will initially focus on a limited geographic region (City) to ensure feasibility within the six-month development timeline.

![UserFlow](/documents/images/UserFlow.png)

---

## 5. Functional Requirements

### 5.1 Core Features

#### 5.1.1 Accessibility Ratings

- Users can:
  - Submit ratings and reviews for destinations based on specific accessibility features (e.g., wheelchair ramps, braille signage).
  - View aggregated ratings and detailed user reviews for listed destinations.
- Administrators can:
  - Moderate user submissions to ensure accurate and appropriate content.

**Accessibility Criteria:**

- **Wheelchair Accessibility**: Presence of ramps, elevators, and step-free paths.
- **Braille Signage**: Availability of braille text for visually impaired users.
- **Hearing Aid Compatibility**: Support for hearing loops or other hearing aid technologies.
- **Accessible Restrooms**: Restrooms equipped with features like grab bars and step-free entry.
- **Parking Accessibility**: Designated parking spots for individuals with disabilities.
- **Seating Options**: Availability of priority seating for individuals with mobility or health needs.
- **Lighting and Visibility**: Proper lighting and contrasting signage for visually impaired users.
- **Sensory-Friendly Options**: Features to support individuals with sensory sensitivities (e.g., quiet zones).
- **Service Animal Friendliness**: Policies and accommodations for service animals.
- **Staff Training**: Staff trained to assist users with specific accessibility needs.

Each of these criteria is clearly explained in the user interface to guide both reviewers and users searching for specific accessibility features.

#### 5.1.2 Travel Planning Tools

- The app will:
  - Allow users to create personalized itineraries with an emphasis on accessible destinations.
  - Integrate map functionality to:
    - Suggest optimized routes that avoid inaccessible areas.
    - Display step-by-step directions tailored to user mobility needs.
  - Include a "Save for Later" option for destinations.

#### 5.1.3 Community Support

- Users can:
  - Participate in forums to share travel tips and ask for recommendations.
  - Recommend and verify accessibility features for destinations.
  - Earn badges or rewards for active contributions to foster community engagement.

### 5.2 Additional Functionalities

#### 5.2.1 User Profiles

- Users can:
  - Create profiles to specify accessibility preferences (e.g., wheelchair access, audio navigation).
  - Update their preferences to receive tailored suggestions.

#### 5.2.2 Notifications

- The app will:
  - Notify users of new reviews, forum activity, or updates to saved destinations.
  - Provide reminders for upcoming trips and itineraries.

#### 5.2.3 Accessibility Database

- The system will:
  - Maintain a database of accessibility ratings and reviews.
  - Use filters to ensure users can search based on specific accessibility requirements.

### 5.3 System Management

#### 5.3.1 Content Moderation

- Administrators will:
  - Review user-submitted content to ensure accuracy and compliance with community guidelines.
  - Flag or remove inappropriate submissions.

#### 5.3.2 Data Backup

- The system will:
  - Perform regular backups of user data and accessibility information to prevent data loss.

#### 5.3.3 Administrator Platform Overview

**Administrator Interface Features:**

- **Content Moderation Dashboard**:

  - Displays all submitted reviews and ratings.
  - Provides tools to approve, edit, or flag content.

- **User Management**:

  - Allows administrators to manage user accounts, including banning abusive users or resetting passwords.

- **Reporting and Analytics**:

  - Displays aggregated data on user activity, accessibility trends, and content performance.

- **Support Tools**:
  - Offers features to communicate directly with users (e.g., responding to flagged reviews or addressing feedback).

**Administrator User Journey Overview:**

1. **Login**: Admin logs into the secure administrator platform.
2. **Moderation**: Reviews submitted content flagged for approval or inappropriate language.
3. **User Support**: Responds to user queries or addresses reported issues through a support dashboard.
4. **Analytics Review**: Monitors app activity, user contributions, and accessibility data trends to improve platform performance.

![FunctionalRequirements](/documents/images/FunctionalRequirements.png)

---

## 6. User Journeys / Use Cases

### 6.1 Use Case: Submitting an Accessibility Rating

**Actors**: Registered User, System

**Preconditions**:

- User must be logged into their account.
- Destination must be listed in the app database.

**Steps**:

1. User navigates to the destination’s details page.
2. User selects the "Add Rating" button.
3. System prompts the user to fill in accessibility details (e.g., wheelchair access, ramps, braille signage).
4. User submits the form.
5. System saves the review and updates the average rating for the destination.
6. System displays a confirmation message to the user.

**Postconditions**:

- The new accessibility rating is visible to all users.

![UseCase](/documents/images/UseCase1.png)

---

### 6.2 Use Case: Creating a Custom Itinerary

**Actors**: Registered User, System

**Preconditions**:

- User must be logged into their account.

**Steps**:

1. User selects the "Create Itinerary" option.
2. System displays a map and search bar for destinations.
3. User searches for and adds destinations to the itinerary.
4. System suggests optimized routes based on accessibility preferences.
5. User saves the itinerary.

**Postconditions**:

- The itinerary is saved and accessible in the user’s profile.

  ![UseCase](/documents/images/UseCase2.png)

---

### 6.3 Use Case: Participating in Community Forums

**Actors**: Registered User, System, Other Community Members

**Preconditions**:

- User must be logged into their account.

**Steps**:

1. User navigates to the "Community Forums" section.
2. User selects a topic or starts a new thread.
3. System allows the user to post a question, tip, or review.
4. Other community members can respond or engage with the post.
5. System notifies the original poster of new replies.

**Postconditions**:

- The post is visible to other community members, fostering engagement.

  ![UseCase](/documents/images/UseCase3.png)

---

### Granular User Journey Analysis (Future Figma Integration Placeholder)

To provide a more comprehensive understanding of user interactions, the following enhancements can be added:

**User Journey: Accessibility Rating Submission**

1. **Login Screen**: User logs into the application. A confirmation message is displayed upon successful login.
2. **Search Page**: User searches for a destination or browses popular options.
3. **Destination Details Page**: Accessibility features and existing ratings are displayed.
4. **Rating Form**: User fills in the form with specific criteria, adding comments and optional photos.
5. **Review Summary**: User reviews the submission before finalizing.
6. **Confirmation Screen**: A "Thank You" message is displayed, and the new rating is instantly visible to others.

**User Journey: Itinerary Creation**

1. **Welcome Dashboard**: User views previously saved itineraries or starts a new one.
2. **Destination Search**: User searches for destinations using filters (e.g., wheelchair accessibility, sensory-friendly options).
3. **Map Integration**: System highlights accessible locations with color-coded markers.
4. **Itinerary Builder**: User adds destinations to the itinerary, rearranging as needed.
5. **Route Optimization**: System calculates accessible routes and displays travel times.
6. **Final Review**: User reviews the completed itinerary, optionally sharing it with others.

**User Journey: Community Engagement**

1. **Community Forums Overview**: User views trending topics or searches for specific discussions.
2. **Thread Interaction**: User replies to an existing thread, upvotes helpful comments, or starts a new discussion.
3. **Profile Recognition**: System highlights active contributors with badges visible in discussions.

---

**Next Steps:**

- Figma designs for each step will be integrated to visualize these journeys further.
- Placeholder diagrams to support these descriptions will be added in subsequent iterations.

## 7. Non-Functional Requirements

### 7.1 Performance

- The app must provide responses to user actions (e.g., loading accessibility data, saving an itinerary) within 2 seconds under normal usage conditions.
  - **Server-Side Actions**: Actions like saving user data or fetching accessibility details should have a response time under 2 seconds for 95% of requests.
  - **Client-Side Actions**: Navigation between app screens or local data retrieval should occur almost instantaneously, within 500 milliseconds.
- The system should handle at least 500 simultaneous users without noticeable performance degradation, with the ability to scale to 1,000 users if demand increases.

### 7.2 Scalability

- The system must support future expansion to accommodate more users, geographic regions, and features.
- The database should be designed to handle up to 1 million entries initially, with the ability to scale incrementally to 10 million entries as the user base grows.
- Scalable cloud solutions (e.g., AWS, Azure, or Google Cloud) will be utilized to manage user growth efficiently.

### 7.3 Accessibility Standards

- The app must adhere to **WCAG 2.1 Level AA** standards for accessibility, ensuring usability for users with disabilities.
  - Features include screen reader compatibility, adjustable text sizes, high-contrast mode, and keyboard navigation.
  - Accessibility testing will be conducted at every development stage to ensure compliance.

### 7.4 Security

- All user data must be encrypted during transmission (using protocols like HTTPS) and storage (AES-256 encryption).
- User authentication will follow best practices, including:
  - Password hashing with algorithms like bcrypt.
  - Two-factor authentication (2FA) for additional security, using SMS codes or authenticator apps.
- Regular penetration testing and vulnerability assessments will be conducted.

#### Privacy

- The app must comply with **GDPR** (General Data Protection Regulation) standards to protect user data:
  - Users must be able to view, edit, and delete their personal data.
  - Data collection will be limited to what is necessary for app functionality, and users will be informed of data usage.
  - A clear privacy policy will be accessible within the app.

### 7.5 Reliability

- The system must have a **99.9% uptime**, with planned maintenance occurring during off-peak hours (e.g., midnight to 4 AM local time).
- Regular backups (daily) should ensure data recovery within 24 hours in case of failure.
  - Incremental backups will be used to minimize downtime and data loss.

### 7.6 Usability

- The app interface must be intuitive and easy to navigate for all user types, regardless of technical proficiency.
- Tutorials or help sections should be available for new users:
  - **Onboarding Tutorial**: A step-by-step walkthrough highlighting key app features (e.g., submitting reviews, creating itineraries).
  - **WCAG-Compliant Help Center**: Includes text-based guides, video tutorials with captions, and accessible formats for visually or hearing-impaired users.

### 7.7 Maintainability

- The codebase should follow clean coding standards, using meaningful naming conventions, modular design, and comprehensive comments/documentation.
- System logs should track errors, performance metrics, and user activity for debugging and optimization.
- **Easy Deployment**:
  - The app should support continuous integration/continuous deployment (CI/CD) pipelines to streamline updates.
  - Patches and hotfixes should be deployable with minimal downtime using tools like Docker or Kubernetes.

![NonFunctionalRequirements](/documents/images/NonFunctionalRequirements.png)

---

## 8. Preliminary Design

### 8.1 UI Concepts

- **Placeholder for Wireframes**: Initial wireframe sketches illustrating the main screens, including the homepage, destination details, itinerary planner, and forums.
- **Placeholder for Color Scheme and Accessibility Considerations**: Notes on color schemes to maintain visual accessibility and support for high-contrast themes.

### 8.2 Navigation Flow

- **Placeholder for Navigation Diagram**: A flowchart showing the relationships between app sections such as Home, Search, Itinerary, and Community Forums.

### 8.3 Technology Stack Visualization

- **Placeholder for System Architecture Diagram**: A diagram outlining the key components of the system, including frontend, backend, and database interactions.

---

## 9. Glossary

| **Term**                 | **Definition**                                                                                     |
| ------------------------ | -------------------------------------------------------------------------------------------------- |
| **Accessibility Rating** | A user-provided evaluation of a destination’s features that support accessibility.                 |
| **Itinerary Planner**    | A tool within the app that allows users to create and save personalized travel plans.              |
| **WCAG 2.1**             | Web Content Accessibility Guidelines, a standard for ensuring digital accessibility.               |
| **High-Contrast Mode**   | A visual theme designed to improve readability for users with visual impairments.                  |
| **Community Forum**      | A feature where users can interact, share experiences, and provide recommendations.                |
| **Content Moderation**   | The process of reviewing and managing user-submitted content to ensure compliance with guidelines. |
| **Braille Signage**      | Text in braille format, used for accessibility for visually impaired individuals.                  |

---
