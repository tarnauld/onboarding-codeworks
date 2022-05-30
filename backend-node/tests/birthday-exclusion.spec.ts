import BirthdayExclusionProcessor from '../src/birthday-exclusion';
import dayjs from 'dayjs';

describe('BirthdayExclusionProcessor', () => {
  it('should return empty list when given empty list', () => {
      const map: Map<string, boolean> = BirthdayExclusionProcessor.execute([]);
    expect(map.size).toEqual(0);
  });

  it('should set the correct flag', () => {
    const users = [
      { name: 'Clara', birthday: new Date() },
      { name: 'John', birthday: dayjs().subtract(7, 'day').toDate() },
    ];
    const map = BirthdayExclusionProcessor.execute(users);

    expect(map.get('Clara')).toBeTruthy();
    expect(map.get('John')).toBeFalsy();
  });
});
