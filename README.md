# Site Survey Tool for Internet Service Providers

## Internship
**Infosys Springboard Internship – Team Project**

---

## Team

### Team Lead
- **Dnyaneshwar Arjun Kharachane**

### Team Members
- Abhinav Thammisetti  
- Shibasis Basu Mallick  
- Ucith Yadav  
- Abhishek P Jadhav  
- Luckey Bhardwaj  
- Kavya S  
- Khushbu Mukatim  
- Bornik Dekaviraj    
---

## Project Overview
The Site Survey Tool is a full-stack web application designed to help Internet Service Providers (ISPs)
plan, document, and manage network installations across large physical locations such as:

- Apartment complexes (MDUs)
- Office buildings (MTUs)
- University and government campuses
- Public and commercial areas

The application allows field engineers to perform structured site surveys before deployment,
reducing installation errors, cost, and rework.

---

## Key Features
- Campus → Building → Floor → Space hierarchy
- Floor plan upload (Image/PDF)
- Space-level data capture
- Checklist-based site survey
- Dynamic survey questions
- Survey response storage
- REST API based architecture
- Secure authentication using JWT

---

## Tech Stack
- **Frontend:** React.js  
- **Backend:** Spring Boot (Java)  
- **Database:** MySQL  
- **Authentication:** JWT (Access & Refresh Tokens)  
- **API Testing:** Postman / Thunder Client  
- **Version Control:** Git & GitHub  

---

## Project Architecture

<img width="503" height="309" alt="image" src="https://github.com/user-attachments/assets/907a148c-e4b2-4aad-a77f-a4f4fe9ef4e3" />

---

## Project Structure

### Backend (Spring Boot)

<img width="319" height="474" alt="image" src="https://github.com/user-attachments/assets/f456b292-ef61-499f-9ae6-5caf2c71f567" />

### Frontend (React)
<img width="490" height="355" alt="image" src="https://github.com/user-attachments/assets/d7640607-b9cf-46e0-a2ad-c47f5826ec55" />

---

## Database Schema (MySQL)

Key tables:
- campus
- building
- floor
- space
- checklist_template
- checklist_question
- checklist_response
- user

Relationships:
- One Campus → Many Buildings
- One Building → Many Floors
- One Floor → Many Spaces
- One Checklist Template → Many Questions
- One Space → Many Survey Responses

---

## Milestone Progress

### ✅ Milestone 1 – Core Setup & Foundations
- Spring Boot project setup
- MySQL configuration
- JWT authentication
- Campus & Building APIs

### ✅ Milestone 2 – Floor Plan & Data Management
- Floor entity & APIs
- Floor plan upload
- Hierarchical data validation

### ✅ Milestone 3 – Labeling & Survey Data Capture
- Space module implementation
- Checklist templates & questions
- Dynamic survey forms
- Survey response capture
- React + Spring Boot integration
- CORS handling and validation

### ⏳ Milestone 4 – Reporting & Finalization
- PDF report generation
- RF integration tools
- Dashboards & analytics
- Deployment & documentation

---

## Screenshots

### Space Selection (React UI)
<img width="907" height="644" alt="image" src="https://github.com/user-attachments/assets/78346e87-3bcc-4ce6-820b-124d6a788ffa" />


### Checklist Survey Form
<!-- Add screenshot here -->

### MySQL Data View
<img width="955" height="780" alt="image" src="https://github.com/user-attachments/assets/27014bc3-1548-4e16-8e15-32274bb4e403" />


---

## Git Workflow
- Feature-based branching strategy
- Separate branch for each milestone
- Pull Requests merged into `develop`
- No direct commits to `main`

---

## Status
🚧 Project under active development as part of Infosys Springboard Internship.





