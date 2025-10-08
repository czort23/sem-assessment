# USE CASE: 6 The top N populated countries in a region where N is provided by the user.

## CHARACTERISTIC INFORMATION

### Goal in Context

The organisation wants to view a report of the top N most populated countries in a region where N is provided by the user.

### Scope

World Population Reporting System

### Level

Primary task.

### Preconditions

Database connection is active.
Database included country data with Region populated.
The user provides a valid region name and integer N.

### Success End Condition

System generates report and displays the top N countries in the specific region, ordered by largest to the smallest population.

### Failed End Condition

If query fails or data unavailable error is displayed.
The user enters an invalid region name or invalid value for N.

### Primary Actor

Organisation user.

### Trigger

"View Top N Countries by Region" is selected, region name is selected and N is specified.

## MAIN SUCCESS SCENARIO

1. User selects navigates to Country Reports -> Top N Countries by Region.
2. The System allows to select or enter region name and integer for N.
3. The user enters the region and number.
4. The System executes the query.
5. Database returns sorted top N countries within that region.
6. User views report.

## EXTENSIONS

3a. The user enters non-numeric or invalid N.
i. System displays "Invalid number. Try again."
3b. The user enters invalid region name.
i. System displays "Please try again."

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0
