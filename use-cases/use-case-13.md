# USE CASE: 13 The top N populated cities in a continent where N is provided by the user.

## CHARACTERISTIC INFORMATION

### Goal in Context

The organisation wants to view a report of the top N most populated cities in a selected continent, where N is selected by the user.

### Scope

World Population Reporting System

### Level

Primary task.

### Preconditions

Database connection is active.
Database contains valid city and country records with continent data.
The user picks/enters a valid continent name.
The user provides a valid integer N.

### Success End Condition

System generates report and displays a list of the top N most populated cities within selected continent, from largest to smallest.

### Failed End Condition

If query fails or data unavailable error is displayed.
Invalid continent name is entered.
Invalid value for N is entered.

### Primary Actor

Organisation user.

### Trigger

"City Reports --> Top N Cities by Continent" is selected.
User picks/enters continent name and value for N.

## MAIN SUCCESS SCENARIO

1. User selects navigates to City Reports --> Top N Cities by Continent.
2. User picks/enters continent name.
3. The user enter a value for N.
4. The System executes the query.
5. Database returns sorted data set.
6. User views report.

## EXTENSIONS

2. The user enters an invalid continent name: "Please enter a valid continent name."
3. The user enters an invalid N value: "Please enter a valid number for N."
5. Database connection fails: "Database unavailable, try again."

## SUB-VARIATIONS

1. Dropdown menu list of continents.
2. Options for N.

## SCHEDULE

**DUE DATE**: Release 1.0
