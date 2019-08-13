/**
 * Created by dattaram on 12/8/19.
 */

import {Action} from 'redux';
export enum CartActionType {
  addToCart = 'addToCart'
}

export class AddToCart implements Action {
  readonly type = CartActionType.addToCart;
  constructor(public data: any[]) {}
}