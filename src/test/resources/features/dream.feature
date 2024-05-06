Feature: 꿈 관련 기능
  Background:
    Given 유저 정보를 생성하고
      | name | email | password |
      | test1 | test1@admin.com | 1234 |
      | test2 | test2@admin.com | 1234 |
      | test3 | test3@admin.com | 1234 |

  Scenario: 유저는 꿈, 마일스톤, 을 생성한다.
    When 유저는 "test1@admin.com", "1234"을 이용해 로그인한다.
    When 유저는 "test1", "descriptionForTest1", "2021-01-01"을 이용해 꿈을 생성한다.
    Then 유저는 꿈을 생성에 성공한다.
