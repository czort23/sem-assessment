# USE CASE: 8 All the cities in a continent organised by largest population to smallest.

## CHARACTERISTIC INFORMATION

### Goal in Context

The organisation wants to view a report of all the cities in the continent organised by largest population to smallest.

### Scope

World Population Reporting System

### Level

Primary task.

### Preconditions

Database connection is active.
Database contains valid city data liked to country records that include continent data.

### Success End Condition

System generates report and displays all cities within selected continent, ordered by largest population to smallest.

### Failed End Condition

If query fails or data unavailable error is displayed.
Invalid continent name is entered.

### Primary Actor

Organisation user.

### Trigger

"City Reports --> Cities by Continent" is selected.

## MAIN SUCCESS SCENARIO

1. User selects navigates to City Reports --> Cities by Continent.
2. The user types/picks continent name.
3. The System executes the query.
4. Database returns sorted data set.
5. User views report.

## EXTENSIONS

2. The user enters an invalid continent name: "Please enter a valid continent name."
3. Database connection fails: "Database unavailable, try again."

## SUB-VARIATIONS

1. Dropdown menu to select a continent.

## SCHEDULE

**DUE DATE**: Release 1.0
