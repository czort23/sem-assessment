# USE CASE: 32 View population by language. 

## CHARACTERISTIC INFORMATION

### Goal in Context

The organisation wants to view a report of total people who speak a specific language, from greatest number to smallest, including the percentage of the world population:
1. Chinese
2. English
3. Hindi
4. Spanish
5. Arabic

### Scope

World Population Reporting System

### Level

Primary task.

### Preconditions

Database connection is active.
Database contains valid country, language and population data.

### Success End Condition

System generates report and displays the total number of people who speak each of the specific language, from most spoken to least.
Report also includes percentage of the world population that speak each language.

### Failed End Condition

If query fails or data unavailable error is displayed.

### Primary Actor

Organisation user.

### Trigger

"Language Report" is selected.

## MAIN SUCCESS SCENARIO

1. User selects navigates to Language Report
2. The System executes the query.
3. Database returns sorted data set.
4. User views report.

## EXTENSIONS

2. Database connection fails: "Database unavailable, try again."

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0
