# USE CASE: 27 The population of a continent.

## CHARACTERISTIC INFORMATION

### Goal in Context

The organisation wants to view a report of the total population of the selected continent.

### Scope

World Population Reporting System

### Level

Primary task.

### Preconditions

Database connection is active.
Database contains valid population data for all countries with correct continent data.
The user provides enters/selects a valid continent name.

### Success End Condition

System generates report and displays the total population of the selected continent.

### Failed End Condition

If query fails or data unavailable error is displayed.

### Primary Actor

Organisation user.

### Trigger

"Population Reports --> Continent Population" is selected.
User enters/selects a valid continent.

## MAIN SUCCESS SCENARIO

1. User selects navigates to Population Reports --> Continent Population.
2. The user enters/selects continent name.
3. The System executes the query.
4. Database returns sorted data set.
5. User views report.

## EXTENSIONS

2. Invalid continent name: "Please enter a valid continent name."
3. Database connection fails: "Database unavailable, try again."

## SUB-VARIATIONS

Dropdown list of valid continents.

## SCHEDULE

**DUE DATE**: Release 1.0
