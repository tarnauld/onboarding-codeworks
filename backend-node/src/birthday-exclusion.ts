import dayjs from 'dayjs';
import isToday from 'dayjs/plugin/isToday';
import isLeapYear from 'dayjs/plugin/isLeapYear';

dayjs.extend(isToday);
dayjs.extend(isLeapYear);

export interface UserBirthday {
  name: string;
  birthday: Date;
}

export default class BirthdayExclusionProcessor {
  public static execute(list: Array<UserBirthday>): Map<string, boolean> {
    const result = new Map();

    list.forEach((userBirthday: UserBirthday) => {
      result.set(userBirthday.name, this.isItBirthday(userBirthday.birthday));
    });

    return result;
  }

  private static isItBirthday(birthday: Date): boolean {
    const today = dayjs();

    const day = dayjs(birthday).get('date');
    const month = dayjs(birthday).get('month');

    if(day === 29 && month === 2 && !dayjs().isLeapYear()) {
      return this.isFirstOfMarch();
    }

    return this.isBirthdayToday(birthday);
  }

  private static isFirstOfMarch(): boolean {
    const today = dayjs();
    return today.get('date') === 1 && today.get('month') === 3;
  }

  private static isBirthdayToday(birthday: Date): boolean {
    const today = dayjs();

    const day = dayjs(birthday).get('date');
    const month = dayjs(birthday).get('month');

    return day === today.get('date') && month === today.get('month');
  }
}
