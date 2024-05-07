Feature: 꿈 관련 기능
  Background:
    Given 유저 정보를 생성하고
      | name | email | password |
      | test1 | test1@admin.com | 1234 |
      | test2 | test2@admin.com | 1234 |
      | test3 | test3@admin.com | 1234 |
    Given 유저로 로그인한다.
      | name | email | password |
      | test1 | test1@admin.com | 1234 |

  Scenario: 유저는 꿈을 생성한다.
    When "test1" 유저는 "test1", "descriptionForTest1", "2021-01-01"을 이용해 꿈을 생성한다.
    Then 유저는 꿈을 생성에 성공한다.

  Scenario: 유저는 마일스톤을 생성한다.
    When "test1" 유저는 "test2", "descriptionForTest1", "2021-01-01"을 이용해 꿈을 생성한다.
    When "test1" 유저는 "test2", "milestone1", "milestoneDescription1"을 이용해 마일스톤을 생성한다.
    Then 유저는 마일스톤을 생성에 성공한다.
