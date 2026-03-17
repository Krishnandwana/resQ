# UI Theme & Design System

## Design Direction

The current home design follows a calm base + strong emergency contrast approach:

- Soft background gradient for psychological calm.
- White structured cards for readability.
- Accent lavender for icons/highlights.
- Red reserved for emergency action emphasis.
- Green reserved for safe/active status signaling.

## Key Theme Tokens (Current)

Defined in `res/values/colors.xml`:

- Base background: `#F9F5F8`
- Secondary background: `#F1ECF5`
- Accent lavender: `#C084CC`
- Soft rose: `#E598B3`
- Card white: `#FFFFFF`
- SOS primary red: `#FF3B30`
- Home SOS tile red: `#E11D48`
- Active green: `#27AE60`
- Warning amber: `#F59E0B`
- Text primary: `#1F1F1F`
- Text secondary: `#6B7280`

## Home-Screen Visual Hierarchy

- Header includes green status pill (`status_pill_bg` + `safe_green`).
- Quick actions use white cards with light lavender border.
- SOS card is isolated with stronger spacing and high contrast.
- Bottom info card uses soft neutral tint (`footer_soft_bg`) to avoid competition with SOS.

## Reusable UI Resources

- `bg_home_gradient.xml` for base background gradient.
- `bg_quick_action_card.xml` for bordered quick-action card internals.
- Dimension tokens in `res/values/dimens.xml` for consistent spacing/text sizes.

## Layout Inventory

Main screens:

- `activity_main.xml`
- `activity_sos.xml`
- `activity_emergency_contacts.xml`
- `activity_helpline.xml`
- `activity_safety_tips.xml`
- `activity_legal_rights.xml`

List/dialog UI:

- `item_contact.xml`, `item_helpline.xml`
- `item_safety_tip.xml`, `item_legal_right.xml`
- `dialog_add_contact.xml`

## Practical UI Rules for Future Changes

1. Keep white as structure, not pink.
2. Keep red strictly for emergency CTAs.
3. Keep green for safe/active/system-ok indicators.
4. Avoid using one accent color for background + icon + text in same section.
5. Preserve spacing around SOS to maintain urgency and focus.
