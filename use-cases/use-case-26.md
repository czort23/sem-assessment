# USE CASE: 26 The population of the world.

## CHARACTERISTIC INFORMATION

### Goal in Context

The organisation wants to view a report of the total population of the world.

### Scope

World Population Reporting System

### Level

Primary task.

### Preconditions

Database connection is active.
Database contains valid population data for all countries.

### Success End Condition

System generates report and displays the total global population of the world.

### Failed End Condition

If query fails or data unavailable error is displayed.

### Primary Actor

Organisation user.

### Trigger

"Population Reports --> Word Population" is selected.

## MAIN SUCCESS SCENARIO

1. User selects navigates to Population Reports --> Word Population.
2. The System executes the query.
3. Database returns sorted data set.
4. User views report.

## EXTENSIONS

2. Database connection fails: "Database unavailable, try again."

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0
