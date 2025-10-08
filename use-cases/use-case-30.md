# USE CASE: 30 The population of a district.

## CHARACTERISTIC INFORMATION

### Goal in Context

The organisation wants to view a report of the total population of the selected district.

### Scope

World Population Reporting System

### Level

Primary task.

### Preconditions

Database connection is active.
Database contains valid city and district records with population data.
User enters/selects a valid district name.

### Success End Condition

System generates report and displays the total population of the selected district.

### Failed End Condition

If query fails or data unavailable error is displayed.

### Primary Actor

Organisation user.

### Trigger

"Population Reports --> District Population" is selected.
User enters/selects a valid district.

## MAIN SUCCESS SCENARIO

1. User selects navigates to Population Reports --> District Population.
2. The user enters/selects district name.
3. The System executes the query.
4. Database returns sorted data set.
5. User views report.

## EXTENSIONS

2. Invalid district name: "Please enter a valid district name."
3. Database connection fails: "Database unavailable, try again."

## SUB-VARIATIONS

Dropdown list of all districts from selected country.

## SCHEDULE

**DUE DATE**: Release 1.0
