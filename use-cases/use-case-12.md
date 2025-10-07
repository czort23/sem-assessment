# USE CASE: 12 The top N populated cities in the world where N is provided by the user.

## CHARACTERISTIC INFORMATION

### Goal in Context

The organisation wants to view a report of the top N most populated cities in the world, where N is selected by the user.

### Scope

World Population Reporting System

### Level

Primary task.

### Preconditions

Database connection is active.
Database contains valid city population data.
The user provides a valid integer N.

### Success End Condition

System generates report and displays a list of the top N cities in the world from largest to the smallest.

### Failed End Condition

If query fails or data unavailable error is displayed.
Invalid N value is entered.

### Primary Actor

Organisation.

### Trigger

"City Reports --> Top N Cities in the World" is selected.

## MAIN SUCCESS SCENARIO

1. User selects navigates to City Reports --> Top N Cities in the World.
2. The user enter a value for N.
3. The System executes the query.
4. Database returns sorted data set.
5. User views report.

## EXTENSIONS

2. The user enters an invalid N value: "Please enter a valid number for N."
3. Database connection fails: "Database unavailable, try again."

## SUB-VARIATIONS

1. Dropdown menu to select a Top N Cities in the World.
2. Dropdown menu to select options for N.

## SCHEDULE

**DUE DATE**: Release 2.0
