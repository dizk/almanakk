# Create GitHub Issue with PRD

You need to interview the user to gather requirements for a Product Requirements Document (PRD) and then create a GitHub
issue with that PRD.

## Interview Process

Interview the user by asking the following questions one at a time. Wait for their response before moving to the next
question. Be conversational and ask follow-up questions when needed for clarity.

### Core Questions to Ask:

1. **Problem Statement**
    - What problem are we trying to solve?
    - Who is experiencing this problem?
    - What is the impact of not solving this problem?

2. **Solution Overview**
    - What is your proposed solution at a high level?
    - Are there any specific technical approaches you have in mind?

3. **User Stories**
    - Who are the primary users/personas?
    - What are the key user journeys or workflows?

4. **Functional Requirements**
    - What are the must-have features?
    - What are the nice-to-have features?
    - Are there any specific UI/UX requirements?

5. **Non-Functional Requirements**
    - Are there performance requirements?
    - Are there security or compliance requirements?
    - Are there scalability requirements?

6. **Success Criteria**
    - How will we measure success?
    - What are the key metrics or KPIs?

7. **Timeline and Milestones**
    - Is there a target timeline?
    - Are there any key milestones or phases?

8. **Dependencies and Risks**
    - Are there any dependencies on other teams or systems?
    - What are the main risks or challenges?

## PRD Template

After gathering all information, create a GitHub issue with the following PRD format:

```markdown
# Product Requirements Document: [Title]

## Executive Summary

[Brief overview of the feature/product]

## Problem Statement

### Problem

[Description of the problem]

### Impact

[Who is affected and how]

### Current State

[How things work today, if applicable]

## Solution Overview

[High-level description of the proposed solution]

## User Personas

- **Persona 1**: [Description]
- **Persona 2**: [Description]

## User Stories

1. As a [persona], I want to [action] so that [benefit]
2. As a [persona], I want to [action] so that [benefit]

## Functional Requirements

### Must Have (P0)

- [ ] Requirement 1
- [ ] Requirement 2

### Should Have (P1)

- [ ] Requirement 1
- [ ] Requirement 2

### Nice to Have (P2)

- [ ] Requirement 1
- [ ] Requirement 2

## Non-Functional Requirements

### Performance

- [Requirement]

### Security

- [Requirement]

### Scalability

- [Requirement]

## Success Criteria

- Metric 1: [Description and target]
- Metric 2: [Description and target]

## Timeline

- **Phase 1**: [Description] - [Timeline]
- **Phase 2**: [Description] - [Timeline]

## Dependencies

- [List any dependencies]

## Risks and Mitigation

| Risk | Impact | Likelihood | Mitigation |
|------|--------|------------|------------|
| Risk 1 | High/Medium/Low | High/Medium/Low | Mitigation strategy |

## Open Questions

- [ ] Question 1
- [ ] Question 2

## Appendix

[Any additional information, mockups, or references]
```

## Creating the GitHub Issue

After completing the PRD, use the `gh` CLI to create the issue:

```bash
gh issue create --title "[PRD] {feature_name}" --body "{prd_content}" --label "PRD,enhancement"
```

Make sure to:

1. Interview thoroughly but be respectful of the user's time
2. Ask clarifying questions when answers are vague
3. Summarize what you've understood periodically
4. Allow the user to review the PRD before creating the issue
5. Ask which repository to create the issue in (if not the current one)

Start by greeting the user and explaining that you'll be interviewing them to create a comprehensive PRD for their
feature request.