# Entity Relationship Diagram - Las Cariñosas Investigation System

## ERD (Mermaid)

```mermaid
erDiagram
    DETECTIVE {
        bigint id PK
        varchar name
        varchar badge_number
        varchar specialization
    }

    CASE {
        bigint id PK
        varchar title
        text description
        varchar status
        timestamp created_at
        bigint detective_id FK
    }

    VICTIM {
        bigint id PK
        varchar name
        varchar location
        text discovery_description
        bigint case_id FK
    }

    SUSPECT {
        bigint id PK
        varchar name
        text description
        varchar status
    }

    EVIDENCE {
        bigint id PK
        text description
        varchar location
        varchar type
        bigint case_id FK
    }

    DETECTIVE ||--o{ CASE : "assigned to"
    CASE ||--o{ VICTIM : "has"
    CASE ||--o{ EVIDENCE : "has"
```

## Relationships

| Relationship | Cardinality | Description |
|---|---|---|
| Detective → Case | One-to-Many | One detective can be assigned to multiple cases |
| Case → Victim | One-to-Many | A case can have multiple victims |
| Case → Evidence | One-to-Many | A case can have multiple pieces of evidence |
| Suspect | Standalone | Suspects exist independently (may be linked to cases via investigation) |
