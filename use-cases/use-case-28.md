# USE CASE: 28 The population of a region.

## CHARACTERISTIC INFORMATION

### Goal in Context

The organisation wants to view a report of the total population of the selected region.

### Scope

World Population Reporting System

### Level

Primary task.

### Preconditions

Database connection is active.
Database contains valid population data for all countries and region data.
User enters/selects a valid region name.

### Success End Condition

System generates report and displays the total population of the selected region.

### Failed End Condition

If query fails or data unavailable error is displayed.

### Primary Actor

Organisation user.

### Trigger

"Population Reports --> Regional Population" is selected.
User enters/selects a valid regional.

## MAIN SUCCESS SCENARIO

1. User selects navigates to Population Reports --> Regional Population.
2. The user enters/selects region name.
3. The System executes the query.
4. Database returns sorted data set.
5. User views report.

## EXTENSIONS

2. Invalid region name: "Please enter a valid region name."
3. Database connection fails: "Database unavailable, try again."

## SUB-VARIATIONS

Dropdown list of valid regions.

## SCHEDULE

**DUE DATE**: Release 1.0
