# USE CASE: 4 The top N populated countries in the world where N is provided by the user.

## CHARACTERISTIC INFORMATION

### Goal in Context

The organisation wants to view a report of the top N populated countries in the world where N is provided by the user.

### Scope

World Population Reporting System

### Level

Primary task.

### Preconditions

Database connection is active.
Database included population data for all countries.
The user provides a valid integer input for N.

### Success End Condition

System generates report and displays the top N countries in the world, ordered by population from largest to smallest.

### Failed End Condition

If query fails or data unavailable error is displayed.
The user enters an invalid or non-numeric value for N.

### Primary Actor

Organisation user.

### Trigger

"View Top N Countries in The World" is selected and N is specified.

## MAIN SUCCESS SCENARIO

1. User selects navigates to Country Reports -> Top N Countries in The World
2. The System allows to type a value for N.
3. The user types the number.
4. The System executes the query.
5. Database returns sorted top N countries.
6. User views report.

## EXTENSIONS

3. The user enters non-numeric or invalid N.
   i. System displays "Invalid number. Try again."

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0
