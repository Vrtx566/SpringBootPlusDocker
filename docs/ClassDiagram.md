# Class Diagram - Las Cariñosas Investigation System

## UML Class Diagram (Mermaid)

```mermaid
classDiagram
    class Detective {
        +Long id
        +String name
        +String badgeNumber
        +String specialization
        +List~Case~ cases
    }

    class Case {
        +Long id
        +String title
        +String description
        +CaseStatus status
        +LocalDateTime createdAt
        +Detective detective
        +List~Victim~ victims
        +List~Evidence~ evidence
        +onCreate()
    }

    class Victim {
        +Long id
        +String name
        +String location
        +String discoveryDescription
        +Case caseEntity
    }

    class Suspect {
        +Long id
        +String name
        +String description
        +SuspectStatus status
    }

    class Evidence {
        +Long id
        +String description
        +String location
        +String type
        +Case caseEntity
    }

    class CaseStatus {
        <<enumeration>>
        OPEN
        CLOSED
        PENDING
    }

    class SuspectStatus {
        <<enumeration>>
        CLEARED
        PERSON_OF_INTEREST
        CHARGED
    }

    class CaseController {
        -CaseService caseService
        +getAll() List~Case~
        +getById(id) ResponseEntity~Case~
        +create(case) Case
        +update(id, case) ResponseEntity~Case~
        +delete(id) ResponseEntity~Void~
    }

    class CaseService {
        -CaseRepository caseRepository
        +findAll() List~Case~
        +findById(id) Optional~Case~
        +save(case) Case
        +deleteById(id) void
    }

    class CaseRepository {
        <<interface>>
        +JpaRepository~Case, Long~
    }

    Detective "1" --> "0..*" Case : assigned to
    Case "1" --> "0..*" Victim : has victims
    Case "1" --> "0..*" Evidence : has evidence
    Case --> CaseStatus : uses
    Suspect --> SuspectStatus : uses
    CaseController --> CaseService : uses
    CaseService --> CaseRepository : uses
    CaseRepository --> Case : manages
```

## Package Structure

```
com.lunalunera.investigation
├── InvestigationApplication.java
├── config/
│   └── SecurityConfig.java
├── controller/
│   ├── CaseController.java
│   ├── VictimController.java
│   ├── SuspectController.java
│   ├── EvidenceController.java
│   └── DetectiveController.java
├── service/
│   ├── CaseService.java
│   ├── VictimService.java
│   ├── SuspectService.java
│   ├── EvidenceService.java
│   └── DetectiveService.java
├── repository/
│   ├── CaseRepository.java
│   ├── VictimRepository.java
│   ├── SuspectRepository.java
│   ├── EvidenceRepository.java
│   └── DetectiveRepository.java
└── model/
    ├── Case.java
    ├── Victim.java
    ├── Suspect.java
    ├── Evidence.java
    ├── Detective.java
    ├── CaseStatus.java
    └── SuspectStatus.java
```
