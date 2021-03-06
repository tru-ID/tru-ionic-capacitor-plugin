export interface TruPluginIonicCapacitorPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
  check(options: { url: string }): Promise<Result>;
  checkWithTrace(options: { url: string }): Promise<Result>;
  isReachable(): Promise<Result>;
}
export interface Result {
  result: string;
}
