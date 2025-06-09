<div align="center">

# Monthly Project Report – Inclusive Trip Planner  
# April 2025

## Document Information

| Description    | Information            |
| -------------- | ---------------------- |
| Document Owner | David Cuahonte Cuevas  |
| Creation Date  | 01/05/2025             |
| Last Update    | 01/05/2025             |

</div>

<br>

<div align="center">

## Objectives for the Month
</div>

- Finalize all backend API endpoints for the application.  
- Implement and validate integration tests for each backend entity.  
- Establish full communication between the frontend and backend.  
- Populate the PostgreSQL database with demo data for testing and presentation.

<br>

<div align="center">

## Progress Summary
</div>

- The backend of the Inclusive Trip Planner was fully implemented:
  - Developed **all necessary API endpoints** using Spring Boot.
  - Created **integration tests** to ensure reliable functionality across entities.
  - Successfully containerized the backend using Docker and connected it to PostgreSQL.
  - Populated the database with sample/demo data to simulate real user interactions.

- Major frontend integration updates:
  - Integrated the frontend with the backend using **Retrofit**.
  - Created matching **models** and **DTOs** to align with backend responses.
  - Refactored **ViewModels** to handle live data from the backend.
  - Updated screens and UI logic to dynamically fetch and display backend data in real time.

<br>

<div align="center">

## Challenges Encountered
</div>

- Connecting the frontend to the backend required significant code adaptation:
  - Frontend models and ViewModels had to be revised to align with backend structure.
  - Retrofitting the UI to accommodate live data introduced several integration edge cases.

<br>

<div align="center">

## Key Goals for Next Month
</div>

- Refine backend logic, structure, and security for a production-ready version.  
- Implement authentication support (Google and Facebook login) with QAuth.  
- Polish and finalize frontend visuals and data flows.  
- Prepare the complete application for demo presentation and user testing.

<br>

<div align="center">

## Conclusion
</div>

April marked a major milestone in the project: the completion and integration of the backend with the mobile frontend. With endpoints tested, data flowing correctly, and UI now reflecting live information, the focus for May will shift toward authentication, visual polish, and final demo preparations. The project is on track for a solid MVP presentation.
