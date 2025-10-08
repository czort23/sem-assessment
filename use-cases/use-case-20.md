# USE CASE: 20 The top N populated capital cities in the world where N is provided by the user.

## CHARACTERISTIC INFORMATION

### Goal in Context

The organisation wants to view a report of all the top N most populated capital cities in the world, where N is provided by the user.

### Scope

World Population Reporting System

### Level

Primary task.

### Preconditions

Database connection is active.
Database contains valid city, country records with correct links between country and capital city.
The user provides a valid number N.

### Success End Condition

System generates report and displays a list of top N capital cities in the world ordered by largest to the smallest population.

### Failed End Condition

User enters invalid N.
If query fails or data unavailable error is displayed.

### Primary Actor

Organisation user.

### Trigger

"City Reports --> Top N Capital Cities" is selected.
User specifies value N.

## MAIN SUCCESS SCENARIO

1. User selects navigates to City Reports --> Top N Capital Cities.
2. The user picks/selects N value.
3. The System executes the query.
4. Database returns sorted data set.
5. User views report.

## EXTENSIONS

2. User typed invalid N value: "Please enter a valid number."
3. Database connection fails: "Database unavailable, try again."

## SUB-VARIATIONS

1. List for N values.
2. Provide filters for continent, region or all.

## SCHEDULE

**DUE DATE**: Release 1.0
